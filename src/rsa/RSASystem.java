package rsa;

import java.math.BigInteger;
import java.util.Random;

public class RSASystem {
    int p, q; // Only 4-digit numbers needed
    BigInteger n, phi, e;

    public RSASystem(int p,  int q) {
        this.p = p;
        this.q = q;

        this.n = BigInteger.valueOf(p).multiply(BigInteger.valueOf(q));
        this.phi = BigInteger.valueOf(p - 1).multiply(BigInteger.valueOf(q - 1));

        e = GenerateE();
    }

    public BigInteger GenerateE() {
        Random rand = new Random();
        BigInteger value;

        do {
            value = new BigInteger(phi.bitLength(), rand); // Generate random BigInteger
        } while (!value.gcd(phi).equals(BigInteger.ONE)); // Ensure coprime

        return value;
    }

    public BigInteger GeneratePrivateKey() {
        return e.modInverse(phi);
    }

    public BigInteger EncryptMessage(BigInteger message) {
        return message.modPow(e, n); // C = M^e mod n
    }

    public BigInteger DecryptMessage(BigInteger ciphertext, BigInteger privateKey) {
        return ciphertext.modPow(privateKey, n); // M = C^d mod n
    }
}
