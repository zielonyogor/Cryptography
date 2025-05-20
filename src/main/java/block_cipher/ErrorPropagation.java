package block_cipher;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Arrays;

public class ErrorPropagation {
    static final int SIZE = 1024;

    static final String[] transformations = new String[]{
        "AES/ECB/PKCS5Padding",
        "AES/CBC/PKCS5Padding",
        "AES/CTR/NoPadding"
    };

    public static void main(String[] args) throws Exception {
        byte[] messageBytes = new byte[SIZE];
        SecureRandom.getInstanceStrong().nextBytes(messageBytes);

        System.out.println("Original message: " + Arrays.toString(messageBytes));

        for(String transformation : transformations) {
            System.out.println("==================================");
            System.out.println("Transformation >>> " + transformation);
            try {
                testPropagation(transformation, messageBytes);
            } catch (Exception e) {
                System.out.println("Error during tests" + e.getMessage());
            }
        }
    }

    static private void testPropagation(String transformation, byte[] message) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey key = keyGenerator.generateKey();

        byte[] iv = null;
        if (transformation.contains("CBC") || transformation.contains("CTR")) {
            iv = new byte[16];
            SecureRandom.getInstanceStrong().nextBytes(iv);
        }

        // --------------- Corrupted message encryption
        byte[] corruptedMessage = Arrays.copyOf(message, message.length);
        corruptedMessage[0] ^= 0b00000001;
        Cipher encryptCipherCorruption = Cipher.getInstance(transformation);
        if (iv != null) {
            encryptCipherCorruption.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        } else {
            encryptCipherCorruption.init(Cipher.ENCRYPT_MODE, key);
        }
        byte[] encryptedCorruptedMessage = encryptCipherCorruption.doFinal(corruptedMessage);

        Cipher decryptCipher1 = Cipher.getInstance(transformation);
        if (iv != null) {
            decryptCipher1.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        } else {
            decryptCipher1.init(Cipher.DECRYPT_MODE, key);
        }
        byte[] decryptedFromCorruptMessage = decryptCipher1.doFinal(encryptedCorruptedMessage);
        System.out.println("Error on original message");
        System.out.println("Found bit errors: " + countBitErrors(message, decryptedFromCorruptMessage));
        printBitDifferences(message, decryptedFromCorruptMessage);

        // ----------------- Correct message encryption
        Cipher encryptCipher = Cipher.getInstance(transformation);
        if (iv != null) {
            encryptCipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        } else {
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
        }
        byte[] encryptedMessage = encryptCipher.doFinal(message);

        // ------------------- Corruption on encryption
        byte[] corruptedEncryption = Arrays.copyOf(encryptedMessage, encryptedMessage.length);
        corruptedEncryption[0] ^= 0b00000001; // one bit change

        Cipher decryptCipher2 = Cipher.getInstance(transformation);
        if(transformation.contains("CBC") || transformation.contains("CTR")) {
            decryptCipher2.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        } else {
            decryptCipher2.init(Cipher.DECRYPT_MODE, key);
        }
        byte[] decryptedMessage = decryptCipher2.doFinal(corruptedEncryption);

        System.out.println("Error on encrypted message");
        System.out.println("Found bit errors: " + countBitErrors(message, decryptedMessage));
        printBitDifferences(message, decryptedMessage);
    }

    static int countBitErrors(byte[] message1, byte[] message2) {
        int errors = 0;
        for(int i = 0; i < Math.min(message1.length, message2.length); i++) {
            errors += Integer.bitCount(message1[i] ^ message2[i]);
        }
        return errors;
    }

    static void printBitDifferences(byte[] message1, byte[] message2) {
        System.out.println("Byte comparison");
        for (int i = 0; i < Math.min(message1.length, message2.length); i++) {
            if (message1[i] != message2[i]) {
                System.out.printf(" - Byte %d: %02X - %02X%n",
                        i, message1[i], message2[i]);
            }
        }
    }
}
