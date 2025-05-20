package block_cipher;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Arrays;

public class CustomCBC {
    static final int BLOCK_SIZE = 16;
    
    public static void main(String[] args) throws Exception{
        byte[] message = "Test message that maybe needs padding".getBytes();
        
        int padding = BLOCK_SIZE - (message.length % BLOCK_SIZE);
        byte[] paddedMessage = Arrays.copyOf(message, message.length + padding);

        for (int i = message.length; i < paddedMessage.length; i++) {
            paddedMessage[i] = (byte) padding;
        }

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey key = keyGenerator.generateKey();

        byte[] iv = new byte[BLOCK_SIZE];
        SecureRandom.getInstanceStrong().nextBytes(iv);

        byte[] encryptedMessage = encrypt(paddedMessage, key, iv);
        byte[] decryptedMessage = decrypt(encryptedMessage, key, iv);

        byte[] result = Arrays.copyOf(decryptedMessage, message.length);
        System.out.println("Original message: " + new String(message));
        System.out.println("Decrypted message: " + new String(result));
    }

    static byte[] encrypt(byte[] message, SecretKey key, byte[] iv) throws Exception {
        Cipher ecb = Cipher.getInstance("AES/ECB/NoPadding");
        ecb.init(Cipher.ENCRYPT_MODE, key);

        byte[] encryptedMessage = new byte[message.length];
        byte[] feedback = iv;

        for (int i = 0; i < message.length; i += BLOCK_SIZE) {
            byte[] block = Arrays.copyOfRange(message, i, i + BLOCK_SIZE);
            byte[] xored = xor(block, feedback);
            byte[] encrypted = ecb.doFinal(xored);
            System.arraycopy(encrypted, 0, encryptedMessage, i, BLOCK_SIZE);
            feedback = encrypted;
        }
        return encryptedMessage;
    }

    static byte[] decrypt(byte[] encryptedMessage, SecretKey key, byte[] iv) throws Exception {
        Cipher ecb = Cipher.getInstance("AES/ECB/NoPadding");
        ecb.init(Cipher.DECRYPT_MODE, key);

        byte[] originalMessage = new byte[encryptedMessage.length];
        byte[] feedback = iv;

        for (int i = 0; i < encryptedMessage.length; i += BLOCK_SIZE) {
            byte[] block = Arrays.copyOfRange(encryptedMessage, i, i + BLOCK_SIZE);
            byte[] decrypted = ecb.doFinal(block);
            byte[] xored = xor(decrypted, feedback);
            System.arraycopy(xored, 0, originalMessage, i, BLOCK_SIZE);
            feedback = block;
        }
        return originalMessage;
    }

    static byte[] xor(byte[] block1, byte[] block2) {
        byte[] result = new byte[block1.length];
        for (int i = 0; i < block1.length; i++) {
            result[i] = (byte)(block1[i] ^ block2[i]);
        }
        return result;
    }
}
