package burrowswheeler.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HuffmanTest {
    @Test
    void testEncode() {
        byte[] encoded = Huffman.compress("ABRACADABRA!");
        assertArrayEquals(new byte[] {0,1,65,0,0,1,68,0,1,33,1,67,0,1,82,1,66,0,0,0,12,124,-76,124,-96}, encoded);
    }

    @Test
    void testDecode() {
        String decoded = Huffman.expand(new byte[] {0,1,65,0,0,1,68,0,1,33,1,67,0,1,82,1,66,0,0,0,12,124,-76,124,-96});
        assertEquals("ABRACADABRA!", decoded);
    }
}
