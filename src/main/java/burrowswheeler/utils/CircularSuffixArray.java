package burrowswheeler.utils;

import java.util.Arrays;
import java.util.Comparator;

public class CircularSuffixArray {
    private final int length;
    private final Integer[] index;

    /**
     * Initializes a circular suffix array based on the input string.
     * @param s the input string.
     * @throws IllegalArgumentException if the input string is null.
     */
    public CircularSuffixArray(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }

        this.length = s.length();
        this.index = createSuffixArray(s);
    }

    /**
     * Creates a suffix array based on the input string.
     *
     * @param s the input string.
     * @return the suffix array.
     */
    private Integer[] createSuffixArray(String s) {
        // Create array of suffixes with circular wrap
        Integer[] suffixIndices = new Integer[length];
        for (int i = 0; i < length; i++) {
            suffixIndices[i] = i;
        }

        // Custom comparator for circular suffix comparison
        Comparator<Integer> circularComparator = (a, b) -> {
            for (int k = 0; k < length; k++) {
                char charA = s.charAt((a + k) % length);
                char charB = s.charAt((b + k) % length);
                if (charA != charB) {
                    return Character.compare(charA, charB);
                }
            }
            return 0;
        };

        // Sort suffix indices based on circular comparison
        Arrays.sort(suffixIndices, circularComparator);

        // Convert to primitive int array
        return suffixIndices;
    }

    /**
     * Returns the length of the input string.
     * @return the length of the input string.
     */
    public int length() {
        return length;
    }

    /**
     * Returns the index of the i-th sorted suffix.
     * @param i the index of the sorted suffix.
     * @return the index of the i-th sorted suffix.
     * @throws IndexOutOfBoundsException if i is out of bounds
     */
    public int index(int i) {
        if (i < 0 || i >= length) {
            throw new IndexOutOfBoundsException();
        }
        return index[i];
    }
}