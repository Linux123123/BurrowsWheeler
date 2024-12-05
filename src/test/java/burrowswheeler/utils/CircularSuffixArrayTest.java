package burrowswheeler.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CircularSuffixArrayTest {

    @Test
    void length() {
        CircularSuffixArray csa = new CircularSuffixArray("ABRACADABRA!");
        assertEquals(12, csa.length());
    }

    @Test
    void index() {
        CircularSuffixArray csa = new CircularSuffixArray("ABRACADABRA!");
        assertEquals(11, csa.index(0));
        assertEquals(10, csa.index(1));
        assertEquals(7, csa.index(2));
        assertEquals(0, csa.index(3));
        assertEquals(3, csa.index(4));
        assertEquals(5, csa.index(5));
        assertEquals(8, csa.index(6));
        assertEquals(1, csa.index(7));
        assertEquals(4, csa.index(8));
        assertEquals(6, csa.index(9));
        assertEquals(9, csa.index(10));
        assertEquals(2, csa.index(11));
    }
}