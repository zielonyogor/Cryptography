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
        // TODO
    }

    public void exchangeKeys() {
        // TODO
    }
}

