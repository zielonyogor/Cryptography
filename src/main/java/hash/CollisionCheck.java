package hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class CollisionCheck {
    public static void main(String[] args) {
        String algorithm = "SHA-256";
        int wordCount = 1000;

        Set<String> words = generateWords(wordCount);
        System.out.println("Generated " + words.size() + " unique 3-letter words.");

        try {
            Map<String, String> bits12 = new HashMap<>();
            Map<String, String> bits20 = new HashMap<>();
            Map<String, String> bits50 = new HashMap<>();

            int collisions12 = 0, collisions20 = 0, collisions50 = 0;

            for (String word : words) {
                byte[] hash = hashMessage(word, algorithm);

                String b12 = getBits(hash, 12);
                String b20 = getBits(hash, 20);
                String b50 = getBits(hash, 50);

                if (bits12.containsKey(b12)) collisions12++;
                else bits12.put(b12, word);

                if (bits20.containsKey(b20)) collisions20++;
                else bits20.put(b20, word);

                if (bits50.containsKey(b50)) collisions50++;
                else bits50.put(b50, word);
            }

            System.out.println("Collisions (12 bits): " + collisions12);
            System.out.println("Collisions (20 bits): " + collisions20);
            System.out.println("Collisions (50 bits): " + collisions50);

        } catch (NoSuchAlgorithmException e) {
            System.out.printf("%-10s: No support.%n", algorithm);
            System.out.println("Error: " + e.getMessage());
        }
    }

    static Set<String> generateWords(int count) {
        Random random = new Random();
        Set<String> words = new HashSet<>();

        while (words.size() < count) {
            char c1 = (char) ('a' + random.nextInt(26));
            char c2 = (char) ('a' + random.nextInt(26));
            char c3 = (char) ('a' + random.nextInt(26));
            words.add("" + c1 + c2 + c3);
        }

        return words;
    }

    static byte[] hashMessage(String message, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        return digest.digest(message.getBytes());
    }

    static String getBits(byte[] hash, int bitCount) {
        StringBuilder bits = new StringBuilder();
        int bitsCollected = 0;

        for (byte b : hash) {
            for (int i = 7; i >= 0 && bitsCollected < bitCount; i--) {
                bits.append((b >> i) & 1);
                bitsCollected++;
            }
            if (bitsCollected >= bitCount) break;
        }

        return bits.toString();
    }
}
