package hash;

import utility.ConsoleFormatter;
import utility.FileManager;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static final String[] ALGORITHMS = {
            "MD5", "SHA-1",
            "SHA-224", "SHA-256", "SHA-384", "SHA-512", "SHA-512/224", "SHA-512/256",
            "SHA3-224", "SHA3-256", "SHA3-384", "SHA3-512"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 10; i++) {
            for(String algorithm : ALGORITHMS) {
                try {
                    hashMessage("warmup", algorithm);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        // Get input from console or read from file
        System.out.println("Would you like to insert input (0) or get from file (anything else)?");
        String inputMessage = scanner.nextLine();

        if(Objects.equals(inputMessage, "0"))
        {
            System.out.println("Message to hash");
            inputMessage = scanner.nextLine();

            System.out.println("Output:");
            output(inputMessage);
        }
        else {
            System.out.println("Output:\n");
            ArrayList<String> messages = FileManager.readFile("hash_input.txt");

            for(String message : messages) {
                System.out.printf("%sMessage      : %s%s%n", ConsoleFormatter.BLUE_BOLD_BRIGHT,message, ConsoleFormatter.RESET);
                output(message);
                System.out.println();
            }
        }
    }

    private static void output(String input) {
        for(String algorithm : ALGORITHMS) {
            try {
                String hash = hashMessage(input, algorithm);
                System.out.printf("%-13s: %s%n", algorithm, hash);
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
