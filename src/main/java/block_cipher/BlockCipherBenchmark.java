package block_cipher;

import org.openjdk.jmh.annotations.*;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class BlockCipherBenchmark {
    @Param({
        "AES/ECB/PKCS5Padding",
        "AES/CBC/PKCS5Padding",
        "AES/CTR/NoPadding"
    })
    public String transformation;

    @Param({
            "262144", // 256 KB
            "524288", // 512 KB
            "1048576" // 1 MB
    })
    public int size;

    private SecretKey key;
    Cipher cipher;

    private byte[] iv;

    byte[] messageBytes, encryptedBytes;

    @Setup(Level.Trial)
    public void initialize() throws Exception {
        messageBytes = new byte[size];
        SecureRandom.getInstanceStrong().nextBytes(messageBytes);

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        key = keyGenerator.generateKey();

        if (transformation.contains("CBC") || transformation.contains("CTR")) {
            iv = new byte[16]; // 128-bit IV
            SecureRandom.getInstanceStrong().nextBytes(iv);
        }

        // for decryption benchmark
        Cipher encryptionCipher = Cipher.getInstance(transformation);
        if (iv != null) {
            encryptionCipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        } else {
            encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
        }
        encryptedBytes = encryptionCipher.doFinal(messageBytes);

        encrypt();
        decrypt();
    }

    @Benchmark
    public byte[] encrypt() throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        if (iv != null) {
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, key);
        }

        return cipher.doFinal(messageBytes);
    }

    @Benchmark
    public byte[] decrypt() throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        if (iv != null) {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        } else {
            cipher.init(Cipher.DECRYPT_MODE, key);
        }

        return cipher.doFinal(encryptedBytes);
    }
}
