package patrones.ejercicio10_p;

public abstract class EstadoCalculadora {
    // Comportamiento por defecto: Si llega una operación cuando no debe, pasa a Error
    public void mas(Calculadora ctx) { ctx.setEstado(new Error()); }
    public void menos(Calculadora ctx) { ctx.setEstado(new Error()); }
    public void por(Calculadora ctx) { ctx.setEstado(new Error()); }
    public void dividido(Calculadora ctx) { ctx.setEstado(new Error()); }
    
    // Cualquier estado puede borrarse y volver a la normalidad
    public void borrar(Calculadora ctx) {
        ctx.setAcumulado(0);
        ctx.setEstado(new Normal());
    }
    
    public abstract String getResultado(Calculadora ctx);
    public abstract void setValor(Calculadora ctx, double unValor);
}

