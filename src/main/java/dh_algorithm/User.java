package dh_algorithm;

import java.math.BigInteger;
import java.util.Random;

public class User {
    String name;
    BigInteger n, g;
    final private int privateKey; // x

    public BigInteger publicKey;
    public BigInteger sharedValue; // here we are giving values from other users

    public User(String name, BigInteger n, BigInteger g) {
        Random random = new Random();
        this.privateKey = Math.abs(random.nextInt());
        System.out.println(name + " has a privateKey = " + privateKey);

        this.n = n;
        this.g = g;

        this.name = name;
    }

    public void setX() {
        publicKey = g.modPow(BigInteger.valueOf(privateKey), n);
    }

    public BigInteger getX() {
        System.out.println(name + " gives an X = " + publicKey);
        return publicKey;
    }

    public void calculateK() {
        publicKey = sharedValue.modPow(BigInteger.valueOf(privateKey), n);
        System.out.println(name + " calculated K = " + publicKey);
    }
}
