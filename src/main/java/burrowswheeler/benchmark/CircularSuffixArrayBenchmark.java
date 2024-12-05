package burrowswheeler.benchmark;

import burrowswheeler.compressor.Compressor;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import burrowswheeler.utils.CircularSuffixArray;

import java.util.concurrent.TimeUnit;
import java.util.Random;

@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(time = 1, timeUnit = TimeUnit.SECONDS)
public class CircularSuffixArrayBenchmark {

    @Param({"10", "100", "1000", "10000", "100000"}) // Sizes of test data in characters
    private int size;

    private String testData;

    @Setup(Level.Iteration)
    public void setup() {
        Random random = new Random(42); // Fixed seed for reproducibility
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            // Generate random printable ASCII characters
            sb.append((char) (random.nextInt(95) + 32));
        }

        testData = sb.toString();
    }

    @Benchmark
    public CircularSuffixArray measureCircularSuffixArrayCreation() {
        return new CircularSuffixArray(testData);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(CircularSuffixArrayBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}