package patrones.ejercicio25_p;

class PlanIntegral extends PlanMedico {
    private static final double MONTO_FIJO = 22000.0;

    @Override
    protected double calcularMontoFijo(Coseguro co) { return MONTO_FIJO; }

    @Override
    protected double calcularCargoFamiliar(double salario, int cantFamiliares, Coseguro co) {
        return (3000.0 * cantFamiliares) + (salario * 0.01);
    }

    @Override
    protected double calcularCoberturaViajera(double salario, Coseguro co) {
        double costoBase = salario * 0.03;
        double descuento = 10000.0 * co.getAntiguedad();
        return Math.max(0, costoBase - descuento);
    }

    @Override
    protected double calcularSeguroInternacion() {
        return MONTO_FIJO * 0.05; // 5% del monto fijo
    }
}