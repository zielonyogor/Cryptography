package dh_algorithm;

import java.math.BigInteger;
import java.util.Random;

public class DHAlgorithm {
    User firstUser, secondUser;
    BigInteger n, g;

    public DHAlgorithm() {
        // encryptionUsers = new ArrayList<>();

        Random random = new Random();
        n = BigInteger.valueOf(4457); // BigInteger.probablePrime(5, random);
        g = BigInteger.valueOf(69);
    }

    public void addUser(String name, int number) {
        User newUser = new User(name, n, g);

        if(number == 1) firstUser = newUser;
        else secondUser = newUser;
    }

    public void exchangeKeys() {
        firstUser.calculateK(secondUser.getX());
        secondUser.calculateK(firstUser.getX());
    }
}

