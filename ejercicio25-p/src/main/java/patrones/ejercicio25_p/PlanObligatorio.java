package patrones.ejercicio25_p;

class PlanObligatorio extends PlanMedico {
    @Override
    protected double calcularMontoFijo(Coseguro co) { return 15000.0; }

    @Override
    protected double calcularCargoFamiliar(double salario, int cantFamiliares, Coseguro co) {
        double costoBase = 3500.0 * cantFamiliares;
        double descuento = co.getDescuento() / 100.0;
        return costoBase * (1.0 - descuento);
    }

    @Override
    protected double calcularCoberturaViajera(double salario, Coseguro co) {
        double costoBase = salario * 0.01;
        // ¡Adiós IF!
        // Si es CoseguroNulo, getMontoCoberturaViajes() devuelve 0.
        // costoBase - 0 = costoBase.
        return costoBase - co.getMontoCoberturaViajes();
    }

    @Override
    protected double calcularSeguroInternacion() { return 0.0; }
}
