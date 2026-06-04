package patrones.ejercicio12_p;

import java.time.LocalDate;

public class Estricta implements PoliticaCancelacion {
	@Override
    public double calcularReembolso(Reserva reserva, LocalDate fechaCancelacion) {
        // No se reembolsa nada
        return 0.0;
    }
}