package patrones.ejercicio12_p;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Moderada implements PoliticaCancelacion {
    @Override
    public double calcularReembolso(Reserva reserva, LocalDate fechaCancelacion) {
        long diasAnticipacion = ChronoUnit.DAYS.between(fechaCancelacion, reserva.getFecha());
        
        if (diasAnticipacion >= 7) {
            return reserva.montoAPagar(); // 100%
        } else if (diasAnticipacion >= 2) {
            return reserva.montoAPagar() * 0.5; // 50%
        } else {
            return 0.0;
        }
    }
}