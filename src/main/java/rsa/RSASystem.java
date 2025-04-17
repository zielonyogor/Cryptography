package rsa;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RSASystem {
    private BigInteger n, phi, e, d;

    public RSASystem(int p,  int q) {
        this.n = BigInteger.valueOf(p).multiply(BigInteger.valueOf(q));
        this.phi = BigInteger.valueOf(p - 1).multiply(BigInteger.valueOf(q - 1));

        e = generateE();
        System.out.println(n);
        d = GeneratePrivateKey();
    }

    public BigInteger generateE() {
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

    public List<BigInteger> encryptMessage(String message) {
        List<BigInteger> encryptedMessage = new ArrayList<>();
        for(char letter : message.toCharArray()) {
            BigInteger asciValue = BigInteger.valueOf(letter);
            encryptedMessage.add(asciValue.modPow(e, n)); // C = M^e mod n
        }
        return encryptedMessage;
    }

    public String decryptMessage(List<BigInteger> ciphertext) {
        StringBuilder decryptedMessage = new StringBuilder();
        for(BigInteger number : ciphertext) {
            int decryptedNumber = number.modPow(d, n).intValue(); // M = C^d mod n
            decryptedMessage.append((char) decryptedNumber);
        }
        return decryptedMessage.toString();
    }


}
