package hash;

import org.openjdk.jmh.annotations.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class HashBenchmark {

    @Param({"Hello World!","Lorem ipsum dolor sit, amet consectetur adipisicing elit. Quas doloremque id delectus fugiat quisquam earum vitae reprehenderit obcaecati nisi vero qui repudiandae, voluptates nobis consectetur in debitis accusantium alias. Suscipit aut ut quo ipsa, quod beatae eligendi labore! Perferendis veniam magni cumque eaque eius vel sit ratione sed voluptates ipsa. Maiores ex possimus saepe at ipsam molestiae omnis tempora ipsum perferendis minus ratione, beatae molestias! Tempora, velit! Voluptatum illo suscipit iure ipsam beatae eos neque quis corrupti, fugiat recusandae in animi eum. Consectetur doloremque eum labore incidunt at. Quibusdam aliquam provident, doloremque doloribus necessitatibus veritatis sunt dignissimos excepturi numquam amet?"})
    public String message;

    @Param({"MD5", "SHA-1",
            "SHA-224", "SHA-256", "SHA-384", "SHA-512", "SHA-512/224", "SHA-512/256",
            "SHA3-224", "SHA3-256", "SHA3-384", "SHA3-512"})
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
