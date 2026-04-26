package patrones.ejercicio5_p;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuimicaTest {

    private Atomo h, o, cl, na, ca;

    @BeforeEach
    public void setUp() {
        // Inicializamos el "catálogo" de elementos
        h = new Atomo("Hidrógeno", "H", 1, 1, false);
        o = new Atomo("Oxígeno", "O", 16, -2, false);
        cl = new Atomo("Cloro", "Cl", 35, -1, false);
        na = new Atomo("Sodio", "Na", 23, 1, true);
        ca = new Atomo("Calcio", "Ca", 40, 2, true);
    }

    @Test
    public void testAgua() {
        Union agua = new Union().agregar(h, 2).agregar(o, 1);
        
        assertEquals("H2O", agua.formula());
        assertEquals(18, agua.pesoMolecular()); // (1*2) + 16
        assertEquals(0, agua.carga());
        assertTrue(agua.esValida());
        assertTrue(agua.esMolecula()); // Al tener carga 0
    }

    @Test
    public void testHidroxidoDeCalcio() {
        // Primero armamos el ion OH (Oxígeno + Hidrógeno)
        Union oh = new Union().agregar(o, 1).agregar(h, 1);
        
        // Luego lo combinamos con el Calcio
        Union hidroxidoDeCalcio = new Union().agregar(ca, 1).agregar(oh, 2);
        
        assertEquals("Ca(OH)2", hidroxidoDeCalcio.formula());
        assertEquals(74, hidroxidoDeCalcio.pesoMolecular()); // 40 + (16+1)*2
        assertEquals(0, hidroxidoDeCalcio.carga());
        assertTrue(hidroxidoDeCalcio.esValida());
    }

    @Test
    public void testUnionInvalida() {
        // Sodio (Metal) + Calcio (Metal)
        Union invalida = new Union().agregar(na, 1).agregar(ca, 1);
        
        assertFalse(invalida.esValida(), "La unión de dos metales debe ser inválida");
    }
}