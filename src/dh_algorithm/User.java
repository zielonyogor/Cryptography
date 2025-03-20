package dh_algorithm;

import java.math.BigInteger;
import java.util.Random;

public class User {
    BigInteger n, g;
    private int privateKey;

    public User(BigInteger n, BigInteger g) {
        Random random = new Random();
        this.privateKey = random.nextInt();

        this.n = n;
        this.g = g;
    }

    public BigInteger GetX() {
        return g.modPow(BigInteger.valueOf(privateKey), n);
    }
}
