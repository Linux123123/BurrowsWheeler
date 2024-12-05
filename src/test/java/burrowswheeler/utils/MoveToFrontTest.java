package burrowswheeler.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MoveToFrontTest {

    @Test
    void encode() {
        String encoded = MoveToFront.encode("ABRACADABRA!");
        assertEquals("ABR\u0002D\u0001E\u0001\u0004\u0004\u0002&", encoded);
    }

    @Test
    void decode() {
        String decoded = MoveToFront.decode("ABR\u0002D\u0001E\u0001\u0004\u0004\u0002&");
        assertEquals("ABRACADABRA!", decoded);
    }
}