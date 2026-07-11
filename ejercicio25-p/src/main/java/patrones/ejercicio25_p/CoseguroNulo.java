package patrones.ejercicio25_p;

import java.time.LocalDate;

public class CoseguroNulo extends Coseguro {

    public CoseguroNulo() {
        // Le pasamos valores por defecto al constructor padre
        super("Sin Coseguro", 0, LocalDate.now(), 0.0);
    }

    // Sobrescribimos los métodos para asegurar que el efecto sea NULO (Cero)
    @Override
    public double getDescuento() { 
        return 0.0; 
    }

    @Override
    public int getAntiguedad() { 
        return 0; 
    }

    @Override
    public double getMontoCoberturaViajes() { 
        return 0.0; 
    }
}
