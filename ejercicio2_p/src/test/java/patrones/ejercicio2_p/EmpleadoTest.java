package patrones.ejercicio2_p;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmpleadoTest {

    private Empleado temporario;
    private Empleado pasante;
    private Empleado planta;

    @BeforeEach
    public void setUp() {
        // Temporario: 10 horas, casado, 1 hijo
        // Basico: 20000 + 3000 = 23000
        // Adicional: 5000 + 2000 = 7000
        // Descuento: (23000 * 0.13) + (7000 * 0.05) = 2990 + 350 = 3340
        // Sueldo: 23000 + 7000 - 3340 = 26660
        temporario = new Temporario(10, true, 1);

        // Pasante: 2 exámenes
        // Basico: 20000
        // Adicional: 4000
        // Descuento: (20000 * 0.13) + (4000 * 0.05) = 2600 + 200 = 2800
        // Sueldo: 20000 + 4000 - 2800 = 21200
        pasante = new Pasante(2);

        // Planta: no casado, 2 hijos, 3 años antiguedad
        // Basico: 50000
        // Adicional: 0 + 4000 + 6000 = 10000
        // Descuento: (50000 * 0.13) + (10000 * 0.05) = 6500 + 500 = 7000
        // Sueldo: 50000 + 10000 - 7000 = 53000
        planta = new Planta(false, 2, 3);
    }

    @Test
    public void testSueldoTemporario() {
        assertEquals(26660.0, temporario.sueldo());
    }

    @Test
    public void testSueldoPasante() {
        assertEquals(21200.0, pasante.sueldo());
    }

    @Test
    public void testSueldoPlanta() {
        assertEquals(53000.0, planta.sueldo());
    }
}