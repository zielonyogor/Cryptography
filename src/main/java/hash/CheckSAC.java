package hash;

import java.security.NoSuchAlgorithmException;

import static hash.Hash.hashMessage;

public class CheckSAC {
    public static void main(String[] args) {
        String originalMessage = "Kot";
        String modifiedMessage = "Kou";
        String algorithm = "SHA-256";

        try {
            byte[] originalHash = hashMessage(originalMessage, algorithm);
            byte[] modifiedHash = hashMessage(modifiedMessage, algorithm);

            String bits1 = getBits(originalHash, 256);
            String bits2 = getBits(modifiedHash, 256);

            int commonBits = countMatchingBits(bits1, bits2);
            double percent = (commonBits / 256.0) * 100;

            System.out.println("Original message: " + originalMessage);
            System.out.println("Modified message: " + modifiedMessage);
            System.out.println("Hash algorithm: " + algorithm);
            System.out.println("Matching bits: " + commonBits + "/256 (" + String.format("%.2f", percent) + "%)");

        } catch (NoSuchAlgorithmException e) {
            System.out.printf("%-10s: No support.%n", algorithm);
            System.out.println("Error: " + e.getMessage());
        }
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

    static int countMatchingBits(String a, String b) {
        int count = 0;
        for (int i = 0; i < Math.min(a.length(), b.length()); i++) {
            if (a.charAt(i) == b.charAt(i)) count++;
        }
        return count;
    }
}
