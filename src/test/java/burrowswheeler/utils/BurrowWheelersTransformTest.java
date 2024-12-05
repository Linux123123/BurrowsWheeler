package burrowswheeler.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BurrowWheelersTransformTest {
    @Test
    void testEncode() {
        String encoded = BurrowsWheelerTransform.encode("ABRACADABRA!");
        assertEquals("3\u0000ARD!RCAAAABB", encoded);
    }

    @Test
    void testDecode() {
        String decoded = BurrowsWheelerTransform.decode("3\u0000ARD!RCAAAABB");
        assertEquals("ABRACADABRA!", decoded);
    }
}
