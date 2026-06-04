package patrones.ejercicio16_p;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;

public class ExcursionTest {

    private Excursion kayak;
    private Usuario u1, u2, u3;

    @BeforeEach
    public void setUp() {
        // Cupo mínimo 1, Cupo máximo 2
        kayak = new Excursion("Dos días en kayak bajando el Paraná", 
                              LocalDate.of(2026, 1, 10), 
                              LocalDate.of(2026, 1, 11), 
                              "Muelle principal", 5000.0, 1, 2);
                              
        u1 = new Usuario("Juan", "Perez", "juan@gmail.com");
        u2 = new Usuario("Ana", "Gomez", "ana@gmail.com");
        u3 = new Usuario("Luis", "Rios", "luis@gmail.com");
    }

    @Test
    public void testInscripcionYTransiciones() {
        // Estado inicial: Provisoria
        assertTrue(kayak.obtenerInformacion().contains("Faltantes para mínimo: 1"));

        // 1. Inscribimos a U1. Debería llegar al cupo mínimo (1) y pasar a Definitiva.
        kayak.inscribir(u1);
        String infoDefinitiva = kayak.obtenerInformacion();
        assertTrue(infoDefinitiva.contains("Faltantes para máximo: 1"));
        assertTrue(infoDefinitiva.contains("juan@gmail.com"));

        // 2. Inscribimos a U2. Debería llegar al cupo máximo (2) y pasar a Llena.
        kayak.inscribir(u2);
        String infoLlena = kayak.obtenerInformacion();
        // En estado llena ya no muestra faltantes ni emails
        assertTrue(!infoLlena.contains("Faltantes"));
        assertTrue(!infoLlena.contains("juan@gmail.com"));

        // 3. Inscribimos a U3. Debería ir a la lista de espera porque ya está Llena.
        kayak.inscribir(u3);
        assertEquals(2, kayak.getInscriptos().size());
        assertEquals(1, kayak.getListaDeEspera().size()); // Luis va a la lista de espera
    }
}