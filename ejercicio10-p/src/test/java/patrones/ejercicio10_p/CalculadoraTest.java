package patrones.ejercicio10_p;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {

    private Calculadora calc;

    @BeforeEach
    public void setUp() {
        calc = new Calculadora();
    }

    @Test
    public void testFlujoNormal() {
        calc.setValor(5);
        calc.mas();
        calc.setValor(3);
        assertEquals("8.0", calc.getResultado());

        calc.por();
        calc.setValor(2);
        assertEquals("16.0", calc.getResultado());
    }

    @Test
    public void testErrorDivisionPorCero() {
        calc.setValor(10);
        calc.dividido();
        calc.setValor(0); // Esto lanza ArithmeticException internamente y pasa a Error
        assertEquals("Error", calc.getResultado());
    }

    @Test
    public void testErrorOperacionConsecutiva() {
        calc.setValor(5);
        calc.mas();
        calc.mas(); // No se le pasó un setValor, tira error según la regla de negocio
        assertEquals("Error", calc.getResultado());
    }

    @Test
    public void testErrorPedirResultadoPrematuro() {
        calc.setValor(5);
        calc.mas();
        // Falta el setValor. Pedir resultado ahora causa error.
        assertEquals("Error", calc.getResultado());
    }

    @Test
    public void testBorrarRestauraCalculadora() {
        calc.setValor(10);
        calc.dividido();
        calc.setValor(0); // Entra en error
        assertEquals("Error", calc.getResultado());

        calc.borrar(); // Sale del error
        assertEquals("0.0", calc.getResultado());
        
        calc.setValor(5);
        calc.mas();
        calc.setValor(5);
        assertEquals("10.0", calc.getResultado());
    }
}