import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorTest {

    @Test
    void testToString() {
        Color color = new Color(0.67, 0.54, 0.32);
        assertEquals("1 1 0\n", color.toString(100));
    }

    @Test
    void clamp() {
        assertEquals(0.6, Color.clamp(0.6, 0.0, 0.999));
        assertEquals(0.0, Color.clamp(-1.0, 0.0, 0.999));
        assertEquals(0.999, Color.clamp(1, 0.0, 0.999));
    }
}