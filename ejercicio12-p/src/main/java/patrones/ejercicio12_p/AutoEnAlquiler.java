package patrones.ejercicio12_p;

import java.time.LocalDate;

public class AutoEnAlquiler {
    private double precioPorDia;
    private int cantidadPlazas;
    private String marca;
    private PoliticaCancelacion politica;

    public AutoEnAlquiler(double precioPorDia, int cantidadPlazas, String marca, PoliticaCancelacion politica) {
        this.precioPorDia = precioPorDia;
        this.cantidadPlazas = cantidadPlazas;
        this.marca = marca;
        this.politica = politica;
    }

    public void setPolitica(PoliticaCancelacion politica) {
        this.politica = politica;
    }

    public double getPrecioPorDia() { return precioPorDia; }

    // El auto delega el cálculo a su estrategia configurada
    public double calcularReembolso(Reserva reserva, LocalDate fechaCancelacion) {
        return politica.calcularReembolso(reserva, fechaCancelacion);
    }
}
