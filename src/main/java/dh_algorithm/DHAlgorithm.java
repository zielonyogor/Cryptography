package dh_algorithm;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DHAlgorithm {
    List<User> encryptionUsers;
    BigInteger n, g;

    public DHAlgorithm() {
        encryptionUsers = new ArrayList<>();

        Random random = new Random();
        n = BigInteger.valueOf(4457); // BigInteger.probablePrime(5, random);
        g = BigInteger.valueOf(69);
    }

    public void addUser(String name) {
        User newUser = new User(name, n, g);
        encryptionUsers.add(newUser);
    }

    public void exchangeKeys() {
        int listLength = encryptionUsers.size();
        System.out.println("\nSetting first values\n");
        for(User user : encryptionUsers) {
            user.setX();
        }

        for (int i = 0; i < listLength - 1; i++) {
            System.out.println("---------------- Iteration: " + i + " ---------------");
            for (int j = 0; j < listLength; j++) {
                System.out.print(encryptionUsers.get(j).name + ": ");
                encryptionUsers.get(j).sharedValue = encryptionUsers.get((j + listLength - 1) % listLength).getX(); // exchanging values
            }

            for(User user : encryptionUsers) {
                user.calculateK(); // calculating new values
            }

            System.out.println();
        }

        System.out.println("Final values: ");
        for(User user : encryptionUsers) {
            System.out.println(user.name + " - " + user.publicKey);
        }
    }
}

