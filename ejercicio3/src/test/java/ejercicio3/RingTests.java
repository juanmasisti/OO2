package ejercicio3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RingTests {

    private CharRing charRing;
    private IntRing intRing;

    @BeforeEach
    public void setUp() {
        charRing = new CharRing("UNLP"); // Longitud 4
        intRing = new IntRing(new int[]{10, 20}); // Longitud 2
    }

    @Test
    public void testCharRingCircular() {
        assertEquals('U', charRing.next());
        assertEquals('N', charRing.next());
        assertEquals('L', charRing.next());
        assertEquals('P', charRing.next());
        // Acá se produce la vuelta circular
        assertEquals('U', charRing.next()); 
    }

    @Test
    public void testIntRingCircular() {
        assertEquals(10, intRing.next());
        assertEquals(20, intRing.next());
        // Acá se produce la vuelta circular
        assertEquals(10, intRing.next()); 
    }
}