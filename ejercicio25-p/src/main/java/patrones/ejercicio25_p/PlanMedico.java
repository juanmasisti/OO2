package patrones.ejercicio25_p;

import java.time.LocalDate;

public abstract class PlanMedico {
    private LocalDate fechaContratacion;

    public PlanMedico() {
        this.fechaContratacion = LocalDate.now();
    }

    // --- TEMPLATE METHOD ---
    // Define el esqueleto inmutable del algoritmo
    public final double calcularCostoTotal(double salario, int cantFamiliares, Coseguro co) {
        return calcularMontoFijo(co) + 
               calcularCargoFamiliar(salario, cantFamiliares, co) + 
               calcularCoberturaViajera(salario,co) + 
               calcularSeguroInternacion();
    }

    // --- OPERACIONES PRIMITIVAS (Ganchos) ---
    protected abstract double calcularMontoFijo(Coseguro co);
    protected abstract double calcularCargoFamiliar(double salario, int cantFamiliares, Coseguro co);
    protected abstract double calcularCoberturaViajera(double salario, Coseguro co);
    protected abstract double calcularSeguroInternacion();
}