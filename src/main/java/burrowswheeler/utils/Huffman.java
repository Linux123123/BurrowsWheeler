package burrowswheeler.utils;

import java.io.ByteArrayOutputStream;
import java.util.PriorityQueue;

public class Huffman {
    private static final int R = 256; // Extended ASCII

    private record Node(char ch, int freq, Huffman.Node left, Huffman.Node right) implements Comparable<Node> {

        private boolean isLeaf() {
                return (left == null) && (right == null);
            }

            @Override
            public int compareTo(Node that) {
                return this.freq - that.freq;
            }
        }

    public static byte[] compress(String input) {
        if (input == null || input.isEmpty()) {
            return new byte[0];
        }

        char[] chars = input.toCharArray();

        // Frequency count
        int[] freq = new int[R];
        for (char c : chars) freq[c]++;

        // Build Huffman trie
        Node root = buildTrie(freq);

        // Build code table
        String[] codeTable = new String[R];
        buildCode(codeTable, root, "");

        // Serialize trie and data
        ByteArrayOutputStream binaryOutput = new ByteArrayOutputStream();

        // Write trie
        writeTrie(root, binaryOutput);

        // Write original input length
        writeInputLength(chars.length, binaryOutput);

        // Write encoded data
        writeEncodedData(chars, codeTable, binaryOutput);

        return binaryOutput.toByteArray();
    }

    public static String expand(byte[] compressed) {
        if (compressed == null || compressed.length == 0) {
            return "";
        }

        // Read trie
        int[] index = {0};
        Node root = readTrie(compressed, index);

        // Read original input length
        int originalLength = readInputLength(compressed, index);

        // Decode data
        return decodeData(compressed, index, root, originalLength);
    }

    private static Node buildTrie(int[] freq) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (char c = 0; c < R; c++) {
            if (freq[c] > 0) {
                pq.offer(new Node(c, freq[c], null, null));
            }
        }

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            assert right != null;
            pq.offer(new Node('\0', left.freq + right.freq, left, right));
        }

        return pq.poll();
    }

//    private static Node buildTrie(int[] freq) {
//        MinPQ<Node> pq = new MinPQ<>();
//        for (char c = 0; c < R; c++) {
//            if (freq[c] > 0) {
//                pq.insert(new Node(c, freq[c], null, null));
//            }
//        }
//
//        while (pq.size() > 1) {
//            Node left = pq.delMin();
//            Node right = pq.delMin();
//            assert right != null;
//            pq.insert(new Node('\0', left.freq + right.freq, left, right));
//        }
//
//        return pq.delMin();
//    }

    private static void buildCode(String[] st, Node x, String s) {
        if (!x.isLeaf()) {
            buildCode(st, x.left, s + '0');
            buildCode(st, x.right, s + '1');
        } else {
            st[x.ch] = s;
        }
    }

    private static void writeTrie(Node x, ByteArrayOutputStream binaryOutput) {
        if (x.isLeaf()) {
            binaryOutput.write(1);  // Marker for leaf node
            binaryOutput.write(x.ch);  // Write character directly
        } else {
            binaryOutput.write(0);  // Marker for internal node
            writeTrie(x.left, binaryOutput);
            writeTrie(x.right, binaryOutput);
        }
    }

    private static void writeInputLength(int length, ByteArrayOutputStream binaryOutput) {
        // Write out all 4 bytes of int32
        binaryOutput.write((length >> 24) & 0xFF);
        binaryOutput.write((length >> 16) & 0xFF);
        binaryOutput.write((length >> 8) & 0xFF);
        binaryOutput.write(length & 0xFF);
    }

    private static void writeEncodedData(char[] chars, String[] codeTable, ByteArrayOutputStream binaryOutput) {
        StringBuilder encodedBits = new StringBuilder();
        for (char c : chars) {
            encodedBits.append(codeTable[c]);
        }

        while (encodedBits.length() % 8 != 0) {
            encodedBits.append('0');
        }

        for (int i = 0; i < encodedBits.length(); i += 8) {
            int byteVal = Integer.parseInt(encodedBits.substring(i, i + 8), 2);
            binaryOutput.write(byteVal);
        }
    }

    private static Node readTrie(byte[] compressed, int[] index) {
        if (index[0] >= compressed.length) {
            throw new IllegalArgumentException("Invalid compressed data");
        }

        if ((compressed[index[0]++] & 0xFF) == 1) {
            // Leaf node
            return new Node((char)(compressed[index[0]++] & 0xFF), 0, null, null);
        }

        // Internal node
        Node left = readTrie(compressed, index);
        Node right = readTrie(compressed, index);
        return new Node('\0', 0, left, right);
    }

    private static int readInputLength(byte[] compressed, int[] index) {
        int length = 0;
        for (int i = 0; i < 4; i++) {
            length = (length << 8) | (compressed[index[0]++] & 0xFF);
        }
        return length;
    }

    private static String decodeData(byte[] compressed, int[] index, Node root, int originalLength) {
        StringBuilder decoded = new StringBuilder();
        Node current = root;

        // Convert compressed bytes to bit string
        StringBuilder bitString = new StringBuilder();
        for (int i = index[0]; i < compressed.length; i++) {
            String bits = String.format("%8s", Integer.toBinaryString(compressed[i] & 0xFF)).replace(' ', '0');
            bitString.append(bits);
        }

        // Decode bits
        for (int i = 0; decoded.length() < originalLength; i++) {
            current = (bitString.charAt(i) == '0') ? current.left : current.right;
            if (current.isLeaf()) {
                decoded.append(current.ch);
                current = root;
            }
        }

        return decoded.toString();
    }
}