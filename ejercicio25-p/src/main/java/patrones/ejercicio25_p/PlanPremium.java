package patrones.ejercicio25_p;

class PlanPremium extends PlanMedico {
    private static final double MONTO_FIJO = 33000.0;

    @Override
    protected double calcularMontoFijo(Afiliado a) {
        if (a.tieneCoseguro()) {
            double descuento = a.getCoseguro().getDescuento() / 100.0;
            return MONTO_FIJO * (1.0 - descuento);
        }
        return MONTO_FIJO;
    }

    @Override
    protected double calcularCargoFamiliar(Afiliado a) {
        if (a.getFamiliaresACargo() <= 4) {
            return 0.0;
        }
        return (a.getFamiliaresACargo() - 4) * 2800.0;
    }

    @Override
    protected double calcularCoberturaViajera(Afiliado a) {
        double costoBase = a.getSalario() * 0.01;
        if (a.tieneCoseguro()) {
            return Math.max(0, costoBase - a.getCoseguro().getMontoCoberturaViajes());
        }
        return costoBase;
    }

    @Override
    protected double calcularSeguroInternacion() {
        return MONTO_FIJO * 0.05; // 5% del monto fijo
    }
}
