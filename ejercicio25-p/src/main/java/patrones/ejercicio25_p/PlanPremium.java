package patrones.ejercicio25_p;

class PlanPremium extends PlanMedico {
    private static final double MONTO_FIJO = 33000.0;

    @Override
    protected double calcularMontoFijo(Coseguro co) {
    	// ¡Adiós IF!
        // Si es un CoseguroNulo, getDescuento() devuelve 0.
    	double descuento = co.getDescuento() / 100.0;
        return MONTO_FIJO * (1.0 - descuento);

    }

    @Override
    protected double calcularCargoFamiliar(double salario, int cantFamiliares, Coseguro co) {
        if (cantFamiliares <= 4) {
            return 0.0;
        }
        return (cantFamiliares - 4) * 2800.0;
    }

    @Override
    protected double calcularCoberturaViajera(double salario, Coseguro co) {
        double costoBase = salario * 0.01;
    	// ¡Adiós IF!
        // Si es un CoseguroNulo, getMontoCoberturaViajes() devuelve 0.
    	double descuento = co.getMontoCoberturaViajes() / 100.0;
        return costoBase - descuento;
    }

    @Override
    protected double calcularSeguroInternacion() {
        return MONTO_FIJO * 0.05; // 5% del monto fijo
    }
}
