package hash;

import org.openjdk.jmh.annotations.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class HashBenchmark {

    @Param({
            "hash_input_1.txt",
            "hash_input_2.txt",
            "hash_input_3.txt"
    })
    public String filename;

    @Param({
            "SHA-256", "MD5", "SHA3-256"
//            "MD5", "SHA-1",
//            "SHA-224", "SHA-256", "SHA-384", "SHA-512", "SHA-512/224", "SHA-512/256",
//            "SHA3-224", "SHA3-256", "SHA3-384", "SHA3-512"
    })
    public String algorithm;

    private String message;

    @Setup(Level.Trial)
    public void loadFile() throws Exception {
        byte[] fileBytes = Files.readAllBytes(Paths.get(filename));
        message = new String(fileBytes, StandardCharsets.UTF_8);
    }

    @Benchmark
    public String hash() throws Exception {
        byte[] input = message.getBytes(StandardCharsets.UTF_8);
        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] output = md.digest(input);
        return bytesToHex(output);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
