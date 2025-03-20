package rsa;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        RSASystem rsa = new RSASystem(7487, 9803); // Small prime numbers

        BigInteger message = BigInteger.valueOf(12);

        BigInteger ciphertext = rsa.EncryptMessage(message);

        BigInteger privateKey = rsa.GeneratePrivateKey();
        BigInteger decryptedMessage = rsa.DecryptMessage(ciphertext, privateKey);

        System.out.println("Original Message: " + message);
        System.out.println("Encrypted Message: " + ciphertext);
        System.out.println("Private Key: " + privateKey);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}
