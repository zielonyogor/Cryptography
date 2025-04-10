package hash;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.MessageDigest;
import java.util.Scanner;

public class Main {
    private static final String[] ALGORITHMS = {
            "MD5", "SHA-1", "SHA-256", "SHA-384"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Message to hash");
        String inputMessage = scanner.nextLine();

        System.out.println("Output:");
        for(String algorithm : ALGORITHMS) {
            try {
                String hash = hashMessage(inputMessage, algorithm);
                System.out.printf("%-10s: %s%n", algorithm, hash);
            } catch (NoSuchAlgorithmException e) {
                System.out.printf("%-10s: No support.%n", algorithm);
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static String hashMessage(String input, String algorithm) throws NoSuchAlgorithmException {
        byte[] messageBytes = input.getBytes(StandardCharsets.UTF_8);
        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] hashBytes = md.digest(messageBytes);

        return bytesToHex(hashBytes);
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
