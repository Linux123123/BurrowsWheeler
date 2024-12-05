package burrowswheeler.main;

import burrowswheeler.compressor.Compressor;

import java.nio.charset.StandardCharsets;

public class ManualTest {
    public static String formatHexDump(byte[] array, int offset, int length) {
        final int width = 16;

        StringBuilder builder = new StringBuilder();

        for (int rowOffset = offset; rowOffset < offset + length; rowOffset += width) {
            builder.append(String.format("%04d:  ", rowOffset));

            for (int index = 0; index < width; index++) {
                if (rowOffset + index < array.length) {
                    builder.append(String.format("%02x ", array[rowOffset + index]));
                } else {
                    builder.append("   ");
                }
            }

            if (rowOffset < array.length) {
                int asciiWidth = Math.min(width, array.length - rowOffset);
                builder.append("  |  ");
                builder.append(new String(array, rowOffset, asciiWidth, StandardCharsets.UTF_8)
                       .replaceAll("\r\n", " ").replaceAll("\n", " "));
            }

            builder.append(String.format("%n"));
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        // Use Compressor to encode and decode a string
        String data = "ABRACADABRA!";
        System.out.println("Original data: " + data);
        System.out.println();
        System.out.println("Original data in hex: ");
        System.out.println(formatHexDump(data.getBytes(), 0, data.length()));

        byte[] encoded = Compressor.encode(data);
        System.out.println("Encoded data: ");
        System.out.println(formatHexDump(encoded, 0, encoded.length));

        String decoded = Compressor.decode(encoded);
        System.out.println("Decoded data: " + decoded);
        System.out.println();
        System.out.println("Decoded data in hex: ");
        System.out.println(formatHexDump(decoded.getBytes(), 0, decoded.length()));
    }
}
