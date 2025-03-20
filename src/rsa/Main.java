package rsa;

import java.math.BigInteger;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RSASystem rsa = new RSASystem(5101, 9803); // Small prime numbers

        String message = "Encrypt message test here";

        List<BigInteger> ciphertext = rsa.EncryptMessage(message);

        String decryptedMessage = rsa.DecryptMessage(ciphertext);

        System.out.println("Original Message: " + message);
        System.out.println("Encrypted Message: " + ciphertext);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}
