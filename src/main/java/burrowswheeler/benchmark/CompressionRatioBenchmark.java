package burrowswheeler.benchmark;

import burrowswheeler.compressor.Compressor;

import java.io.IOException;
import java.nio.file.*;

public class CompressionRatioBenchmark {

    private static final String DATA_DIRECTORY = "data"; // Update this to your directory path

    public static void main(String[] args) {
        try {
            Path dirPath = Paths.get(CompressionRatioBenchmark.DATA_DIRECTORY);
            double sum = 0;
            int n = 0;

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath)) {
                for (Path filePath : stream) {
                    System.gc(); // Run the garbage collector before each file to reduce noise
                    if (Files.isRegularFile(filePath)) {
                        String content = Files.readString(filePath);
                        String fileName = filePath.getFileName().toString();

                        // Compress the content
                        byte[] compressed = Compressor.encode(content);

                        // Compare sizes
                        int originalSize = content.getBytes().length;
                        int compressedSize = compressed.length;

                        // Calculate compression ratio
                        double compressionRatio = (double) compressedSize / originalSize * 100;
                        if (compressionRatio < 100) {
                            sum += compressionRatio;
                            n++;
                        }

                        // Output file details, sizes, and compression ratio
                        System.out.printf("File: %s, Original size: %d bytes, Compressed size: %d bytes, Compression ratio: %.2f%%%n",
                                fileName, originalSize, compressedSize, compressionRatio);

                        // Decompress the content
                        String decompressed = Compressor.decode(compressed);

                        // Verify that the decompressed content matches the original
                        if (!content.equals(decompressed)) {
                            throw new AssertionError("Decompressed data does not match original for file: " + fileName);
                        }
                    }
                }
            }

            // Calculate average compression ratio
            double averageCompressionRatio = sum / n;
            System.out.printf("Average compression ratio: %.2f%%%n", averageCompressionRatio);


        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

}
