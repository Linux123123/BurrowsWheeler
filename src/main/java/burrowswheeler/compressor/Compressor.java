package burrowswheeler.compressor;

import burrowswheeler.utils.*;

public class Compressor {
    public static byte[] encode(String data) {
        String bwt = BurrowsWheelerTransform.encode(data);
        String mtf = MoveToFront.encode(bwt);
        return Huffman.compress(mtf);
    }

    public static String decode(byte[] data) {
        String huffman = Huffman.expand(data);
        String mtf = MoveToFront.decode(huffman);
        return BurrowsWheelerTransform.decode(mtf);
    }
}