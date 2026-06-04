package patrones.ejercicio25_p;

class PlanIntegral extends PlanMedico {
    private static final double MONTO_FIJO = 22000.0;

    @Override
    protected double calcularMontoFijo(Afiliado a) { return MONTO_FIJO; }

    @Override
    protected double calcularCargoFamiliar(Afiliado a) {
        return (3000.0 * a.getFamiliaresACargo()) + (a.getSalario() * 0.01);
    }

    @Override
    protected double calcularCoberturaViajera(Afiliado a) {
        double costoBase = a.getSalario() * 0.03;
        if (a.tieneCoseguro()) {
            double descuento = 10000.0 * a.getCoseguro().getAntiguedad();
            return Math.max(0, costoBase - descuento);
        }
        return costoBase;
    }

    @Override
    protected double calcularSeguroInternacion() {
        return MONTO_FIJO * 0.05; // 5% del monto fijo
    }
}