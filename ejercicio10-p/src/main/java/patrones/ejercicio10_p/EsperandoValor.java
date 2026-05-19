package patrones.ejercicio10_p;

public class EsperandoValor extends EstadoCalculadora {
    @Override
    public String getResultado(Calculadora ctx) {
        // Si piden resultado sin haber pasado el 2do valor -> Error
        ctx.setEstado(new Error());
        return "Error";
    }

    @Override
    public void setValor(Calculadora ctx, double unValor) {
        try {
            double nuevoAcumulado = ctx.getOperacion().calcular(ctx.getAcumulado(), unValor);
            ctx.setAcumulado(nuevoAcumulado);
            ctx.setEstado(new Normal());
        } catch (ArithmeticException e) {
            ctx.setEstado(new Error()); // Manejo de la división por cero
        }
    }
    // Los métodos mas, menos, por, dividido heredan el comportamiento por defecto (van a Error)
}
