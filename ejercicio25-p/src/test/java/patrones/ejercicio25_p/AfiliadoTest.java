package patrones.ejercicio25_p;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class AfiliadoTest {

    private Afiliado pedro;

    @BeforeEach
    public void setUp() {
        // Pedro: 27 años, salario 100.000, 2 familiares a cargo, Plan Obligatorio
        pedro = new Afiliado("Pedro", 2, 100000.0, LocalDate.of(1999, 1, 1), new PlanObligatorio());
    }

    @Test
    public void testCalculoPlanObligatorioSinCoseguro() {
        // Monto Fijo: 15000
        // Familiar: 3500 * 2 = 7000
        // Viajera: 1% de 100000 = 1000
        // Internacion: 0
        // Total esperado = 23000.0
        assertEquals(23000.0, pedro.calcularMonto());
    }

    @Test
    public void testCambioAPlanIntegralSinCoseguro() {
        pedro.setPlanMedico(new PlanIntegral());
        
        // Monto Fijo: 22000
        // Familiar: (3000 * 2) + (1% de 100000) = 6000 + 1000 = 7000
        // Viajera: 3% de 100000 = 3000
        // Internacion: 5% de 22000 = 1100
        // Total esperado = 33100.0
        assertEquals(33100.0, pedro.calcularMonto());
    }

    @Test
    public void testPlanIntegralConCoseguro() {
        pedro.setPlanMedico(new PlanIntegral());
        
        // Creamos un coseguro con 2 años de antigüedad (ingresó en 2024 si estamos en 2026)
        // 20% descuento genérico, monto cobertura viajes 500
        Coseguro coseguro = new Coseguro("OSDE", 20, LocalDate.now().minusYears(2), 500.0);
        pedro.setCoseguro(coseguro);
        
        // Monto Fijo: 22000
        // Familiar: 7000 (el Plan Integral no tiene descuento por coseguro aquí)
        // Viajera: 3000 base - (10000 * 2 años) = 0 (topeado en 0 por Math.max)
        // Internacion: 1100
        // Total esperado = 22000 + 7000 + 0 + 1100 = 30100.0
        assertEquals(30100.0, pedro.calcularMonto());
    }
}