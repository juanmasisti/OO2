package patrones.ejercicio12_p;

import java.time.LocalDate;

public interface PoliticaCancelacion {
    double calcularReembolso(Reserva reserva, LocalDate fechaCancelacion);
}