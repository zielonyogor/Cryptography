package dh_algorithm;

import java.math.BigInteger;
import java.util.Random;

public class User {
    String name;
    BigInteger n, g;
    private int privateKey;

    public User(String name, BigInteger n, BigInteger g) {
        Random random = new Random();
        this.privateKey = Math.abs(random.nextInt());
        System.out.println(name + " has a privateKey = " + privateKey);

        this.n = n;
        this.g = g;

        this.name = name;
    }

    public BigInteger GetX() {
        System.out.println(name + " gives an X = " + g.modPow(BigInteger.valueOf(privateKey), n));
        return g.modPow(BigInteger.valueOf(privateKey), n);
    }

    public void CalculateK(BigInteger x) {
        BigInteger k = x.modPow(BigInteger.valueOf(privateKey), n);
        System.out.println(name + " calculated K = " + k);
    }
}
