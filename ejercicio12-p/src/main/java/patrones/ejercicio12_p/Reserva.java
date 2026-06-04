package patrones.ejercicio12_p;

import java.time.LocalDate;

public class Reserva {
    private int cantidadDias;
    private LocalDate fecha; // Fecha de inicio de la reserva
    private AutoEnAlquiler auto;

    public Reserva(int cantidadDias, LocalDate fecha, AutoEnAlquiler auto) {
        this.cantidadDias = cantidadDias;
        this.fecha = fecha;
        this.auto = auto;
    }

    public LocalDate getFecha() { return fecha; }

    public double montoAPagar() {
        return auto.getPrecioPorDia() * cantidadDias;
    }

    // La reserva le pide al auto que calcule el reembolso según su política
    public double montoAReembolsar(LocalDate fechaCancelacion) {
        return auto.calcularReembolso(this, fechaCancelacion);
    }
}

