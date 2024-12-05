package burrowswheeler.utils;

public class BurrowsWheelerTransform {
    public static final int R = 256;

    /**
     * Apply Burrows-Wheeler encoding to the input data
     *
     * @param data The input data to encode
     * @return The encoded data
     */
    public static String encode (String data) {
        if (data == null) throw new IllegalArgumentException("Input data cannot be null");

        CircularSuffixArray originalPos = new CircularSuffixArray(data);
        StringBuilder encoded = new StringBuilder();
        int orig = 0;
        for (int i = 0; i < data.length(); i++) {
            int pos = originalPos.index(i) - 1;
            if (pos < 0) {
                pos = data.length() + pos;
            }
            encoded.append(data.charAt(pos));
            if (originalPos.index(i) == 0) orig = i;
        }

        return orig + "\0" + encoded;
    }

    /**
     * Apply Burrows-Wheeler decoding to the input data
     *
     * @param data The input data to decode
     * @return The decoded data
     */
    public static String decode (String data) {
        if (data == null) throw new IllegalArgumentException("Input data cannot be null");

        String[] parts = data.split("\0", 2);
        int orig = Integer.parseInt(parts[0]);
        String last = parts[1];

        int[] next = new int[last.length()];
        int[] count = new int[R + 1];
        for (int i = 0; i < last.length(); i++) {
            count[last.charAt(i) + 1]++;
        }
        for (int i = 1; i < count.length - 1; i++) {
            count[i + 1] += count[i];
        }
        for(int i = 0; i < last.length(); i++) {
            next[count[last.charAt(i)]++] = i;
        }

        char[] ret = new char[next.length];

        int curr = orig;
        for(int i = 0; i < next.length - 1; i++) {
            ret[i] = last.charAt(next[curr]);
            curr = next[curr];
        }

        ret[next.length - 1] = last.charAt(orig);

        return new String(ret);
    }
}
