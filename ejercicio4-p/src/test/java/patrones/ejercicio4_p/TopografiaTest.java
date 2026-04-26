package patrones.ejercicio4_p;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TopografiaTest {

    private Topografia agua;
    private Topografia tierra;
    private Topografia pantano;
    private Topografia mixtaC;
    private Topografia mixtaD;

    @BeforeEach
    public void setUp() {
        agua = new Agua();
        tierra = new Tierra();
        pantano = new Pantano();

        // Figura (c): 2 partes de agua y 2 de tierra.
        // Proporción de agua = (1 + 0 + 0 + 1) / 4 = 0.5
        mixtaC = new Mixta(new Agua(), new Tierra(), new Tierra(), new Agua());

        // Figura (d): Agua, Tierra, Tierra, y una Mixta adentro.
        // La Mixta de adentro es igual a la (c).
        // Proporción de agua = (1 + 0 + 0 + 0.5) / 4 = 0.375
        mixtaD = new Mixta(new Agua(), new Tierra(), new Tierra(), 
                           new Mixta(new Agua(), new Tierra(), new Tierra(), new Agua()));
    }

    @Test
    public void testProporcionAgua() {
        assertEquals(1.0, agua.proporcionAgua());
        assertEquals(0.0, tierra.proporcionAgua());
        assertEquals(0.7, pantano.proporcionAgua());
        
        assertEquals(0.5, mixtaC.proporcionAgua());
        assertEquals(0.375, mixtaD.proporcionAgua());
    }

    @Test
    public void testProporcionTierra() {
        assertEquals(0.0, agua.proporcionTierra());
        assertEquals(1.0, tierra.proporcionTierra());
        assertEquals(0.3, pantano.proporcionTierra(), 0.001); // Margen de error por float
        
        assertEquals(0.5, mixtaC.proporcionTierra());
        assertEquals(0.625, mixtaD.proporcionTierra());
    }

    @Test
    public void testEquals() {
        // Igualdad simple
        assertTrue(agua.equals(new Agua()));
        assertFalse(agua.equals(tierra));
        
        // Igualdad compleja
        Topografia otraMixtaC = new Mixta(new Agua(), new Tierra(), new Tierra(), new Agua());
        assertTrue(mixtaC.equals(otraMixtaC));
        
        // Distinta disposición no es igual
        Topografia mixtaDiferenteOrden = new Mixta(new Tierra(), new Agua(), new Tierra(), new Agua());
        assertFalse(mixtaC.equals(mixtaDiferenteOrden));
        
        assertFalse(mixtaC.equals(mixtaD));
    }
}