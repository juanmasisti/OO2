package patrones.ejercicio25_p;

class PlanObligatorio extends PlanMedico {
    @Override
    protected double calcularMontoFijo(Afiliado a) { return 15000.0; }

    @Override
    protected double calcularCargoFamiliar(Afiliado a) {
        double costoBase = 3500.0 * a.getFamiliaresACargo();
        if (a.tieneCoseguro()) {
            double descuento = a.getCoseguro().getDescuento() / 100.0;
            return costoBase * (1.0 - descuento);
        }
        return costoBase;
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
    protected double calcularSeguroInternacion() { return 0.0; }
}
