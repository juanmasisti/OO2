package patrones.ejercicio10_p;

public class Normal extends EstadoCalculadora {
    @Override
    public String getResultado(Calculadora ctx) {
        return String.valueOf(ctx.getAcumulado());
    }

    @Override
    public void setValor(Calculadora ctx, double unValor) {
        ctx.setAcumulado(unValor);
    }

    @Override
    public void mas(Calculadora ctx) { prepararOperacion(ctx, new Suma()); }

    @Override
    public void menos(Calculadora ctx) { prepararOperacion(ctx, new Resta()); }

    @Override
    public void por(Calculadora ctx) { prepararOperacion(ctx, new Multiplicacion()); }

    @Override
    public void dividido(Calculadora ctx) { prepararOperacion(ctx, new Division()); }

    private void prepararOperacion(Calculadora ctx, OperacionStrategy op) {
        ctx.setOperacion(op);
        ctx.setEstado(new EsperandoValor());
    }
}
