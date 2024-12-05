package burrowswheeler.benchmark;

import burrowswheeler.utils.Huffman;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(time = 1, timeUnit = TimeUnit.SECONDS)
public class HuffmanBenchmark {
    @Param({"10", "100", "1000", "10000", "100000"}) // Sizes of test data in characters
    private int size;

    private String testData;
    private byte[] compressedData;

    @Setup(Level.Iteration)
    public void setup() {
        Random random = new Random(42); // Fixed seed for reproducibility
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            // Generate random printable ASCII characters
            sb.append((char) (random.nextInt(95) + 32));
        }

        testData = sb.toString();

        // Pre-compute compressed data for decode benchmarks
        compressedData = Huffman.compress(testData);
    }

    @Benchmark
    public byte[] huffmanEncode() {
        return Huffman.compress(testData);
    }

    @Benchmark
    public String huffmanDecode() {
        return Huffman.expand(compressedData);
    }

    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder()
                .include(HuffmanBenchmark.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(options).run();
    }
}