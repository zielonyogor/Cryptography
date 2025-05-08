package hash;

import utility.ConsoleFormatter;
import utility.FileManager;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static hash.Hash.*;

public class Main {
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
            ArrayList<String> messages = FileManager.readFile("hash_input_1.txt");

            for(String message : messages) {
                System.out.printf("%sMessage      : %s%s%n", ConsoleFormatter.BLUE_BOLD_BRIGHT,message, ConsoleFormatter.RESET);
                output(message);
                System.out.println();
            }
        }
    }
}
