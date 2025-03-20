package bbs;

import java.math.BigInteger;
import java.util.Random;

public class BBSGenerator {
    BigInteger m;
    int outputlength;

    public BBSGenerator(int p, int q, int n) {
        m = BigInteger.valueOf((long) p*q);
        outputlength = n;
    }

    public String GetBBSOutput() {
        BigInteger x = GetSeed(m);
        StringBuilder bitOutput = new StringBuilder();
        for (int i = 0; i < outputlength; i++) {
            x = x.multiply(x).mod(m);  // x = (x^2) mod m
            bitOutput.append(x.mod(BigInteger.TWO)); // Modulo 2 is getting last bit
        }
        return bitOutput.toString();
    }

    public static BigInteger GetSeed(BigInteger m) {
        Random rand = new Random();
        BigInteger seed;

        do {
            seed = new BigInteger(m.bitLength(), rand); // Generate random BigInteger
        } while (!seed.gcd(m).equals(BigInteger.ONE)); // Ensure coprime

        return seed;
    }
}
