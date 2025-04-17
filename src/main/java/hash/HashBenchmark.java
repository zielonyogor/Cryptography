package hash;

import org.openjdk.jmh.annotations.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class HashBenchmark {

    @Param({"Hello world", "Some longer text for hashing"})
    public String message;

    @Param({"SHA-256", "SHA-1", "MD5"})
    public String algorithm;

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
