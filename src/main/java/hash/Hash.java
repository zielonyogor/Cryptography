package hash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    public static final String[] ALGORITHMS = {
            "MD5", "SHA-1",
            "SHA-224", "SHA-256", "SHA-384", "SHA-512", "SHA-512/224", "SHA-512/256",
            "SHA3-224", "SHA3-256", "SHA3-384", "SHA3-512"
    };

    public static void output(String input) {
        for(String algorithm : ALGORITHMS) {
            try {
                String hash = bytesToHex(hashMessage(input, algorithm));
                System.out.printf("%-13s: %s%n", algorithm, hash);
            } catch (NoSuchAlgorithmException e) {
                System.out.printf("%-10s: No support.%n", algorithm);
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static byte[] hashMessage(String input, String algorithm) throws NoSuchAlgorithmException {
        byte[] messageBytes = input.getBytes(StandardCharsets.UTF_8);
        MessageDigest md = MessageDigest.getInstance(algorithm);

        return md.digest(messageBytes);
    }
    public static byte[] hashMessage(byte[] input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);

        return md.digest(input);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);

            if (hex.length() == 1)
                hexString.append('0');

            hexString.append(hex);
        }

        return hexString.toString();
    }
}
