package burrowswheeler.utils;

import java.util.LinkedList;
import java.util.List;

public class MoveToFront {
    private static final int R = 256;

    /**
     * Apply move-to-front encoding to the input data.
     *
     * @param data the input data
     * @return the encoded data
     */
    public static String encode(String data) {
        StringBuilder out = new StringBuilder(data.length());

        // Initialize the list with 256 ASCII characters
        List<Character> index = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            index.add((char) i);
        }

        for (char c : data.toCharArray()) {
            int pos = index.indexOf(c);
            out.append((char) pos); // Encode position
            index.remove(pos);
            index.add(0, c); // Move to front
        }

        return out.toString();
    }

    /**
     * Apply move-to-front decoding to the input data.
     *
     * @param data the input data
     * @return the decoded data
     */
    public static String decode(String data) {
        StringBuilder out = new StringBuilder(data.length());

        // Initialize the list with 256 ASCII characters
        List<Character> index = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            index.add((char) i);
        }

        for (char pos : data.toCharArray()) {
            char c = index.get(pos);
            out.append(c); // Decode character
            index.remove(pos);
            index.add(0, c); // Move to front
        }

        return out.toString();
    }
}
