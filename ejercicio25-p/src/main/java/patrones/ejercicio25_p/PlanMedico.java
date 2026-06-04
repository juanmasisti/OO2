package patrones.ejercicio25_p;

import java.time.LocalDate;

public abstract class PlanMedico {
    private LocalDate fechaContratacion;

    public PlanMedico() {
        this.fechaContratacion = LocalDate.now();
    }

    // --- TEMPLATE METHOD ---
    // Define el esqueleto inmutable del algoritmo
    public final double calcularCostoTotal(Afiliado a) {
        return calcularMontoFijo(a) + 
               calcularCargoFamiliar(a) + 
               calcularCoberturaViajera(a) + 
               calcularSeguroInternacion();
    }

    // --- OPERACIONES PRIMITIVAS (Ganchos) ---
    protected abstract double calcularMontoFijo(Afiliado a);
    protected abstract double calcularCargoFamiliar(Afiliado a);
    protected abstract double calcularCoberturaViajera(Afiliado a);
    protected abstract double calcularSeguroInternacion();
}