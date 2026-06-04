package patrones.ejercicio12_p;

import java.time.LocalDate;

class Flexible implements PoliticaCancelacion {
    @Override
    public double calcularReembolso(Reserva reserva, LocalDate fechaCancelacion) {
        // Se reembolsa el 100% siempre (asumiendo que cancela antes del inicio)
        return reserva.montoAPagar();
    }
}
