import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vec3Test {

    @Test
    void add() {
        Vec3 one = new Vec3(1, 1, 1);
        Vec3 two = new Vec3(4, 5, 6);
        Vec3 expected = new Vec3(5, 6, 7);
        Vec3 actual = one.add(two);

        assertTrue(expected.equals(actual));
    }

    @Test
    void mul() {
        Vec3 one = new Vec3(1, 1, 1);
        double t = 5.0;
        Vec3 expected = new Vec3(5.0, 5.0, 5.0);
        Vec3 actual = one.mul(t);

        assertTrue(expected.equals(actual));
    }

    @Test
    void div() {
        Vec3 one = new Vec3(10, 15, 20);
        double t = 5.0;
        Vec3 expected = new Vec3(2.0, 3.0, 4.0);
        Vec3 actual = one.div(t);

        assertTrue(expected.equals(actual));
    }

    @Test
    void sub() {
        Vec3 one = new Vec3(1, 1, 1);
        Vec3 two = new Vec3(4, 5, 6);
        Vec3 expected = new Vec3(-3, -4, -5);
        Vec3 actual = one.sub(two);

        assertTrue(expected.equals(actual));
    }

    @Test
    void testMul() {
        Vec3 one = new Vec3(1, 1, 1);
        Vec3 two = new Vec3(4, 5, 6);
        Vec3 expected = new Vec3(4, 5, 6);
        Vec3 actual = one.mul(two);

        assertTrue(expected.equals(actual));
    }

    @Test
    void testDiv() {
        Vec3 one = new Vec3(12, 15, 18);
        Vec3 two = new Vec3(4, 5, 6);
        Vec3 expected = new Vec3(3, 3, 3);
        Vec3 actual = one.div(two);

        assertTrue(expected.equals(actual));
    }

    @Test
    void dot() {
        Vec3 one = new Vec3(1, 1, 1);
        Vec3 two = new Vec3(4, 5, 6);
        double expected = 15.0;
        double actual = one.dot(two);

        assertTrue(expected == actual);
        assertEquals(expected, actual);
    }

    @Test
    void cross() {
        Vec3 one = new Vec3(1, 1, 1);
        Vec3 two = new Vec3(4, 5, 6);
        Vec3 expected = new Vec3(1, -2, 1);
        Vec3 actual = one.cross(two);

        assertTrue(expected.equals(actual));
    }
}