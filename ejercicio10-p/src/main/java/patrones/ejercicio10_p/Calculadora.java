package patrones.ejercicio10_p;

public class Calculadora {
    private double acumulado;
    private EstadoCalculadora estado;
    private OperacionStrategy operacionActual;

    public Calculadora() {
        this.acumulado = 0;
        this.estado = new Normal();
    }

    // --- Protocolo Público (Delegación al State) ---
    public String getResultado() { return estado.getResultado(this); }
    public void borrar() { estado.borrar(this); }
    public void setValor(double unValor) { estado.setValor(this, unValor); }
    public void mas() { estado.mas(this); }
    public void menos() { estado.menos(this); }
    public void por() { estado.por(this); }
    public void dividido() { estado.dividido(this); }

    // --- Métodos de visibilidad de paquete/protected para el State ---
    protected void setEstado(EstadoCalculadora estado) { this.estado = estado; }
    protected double getAcumulado() { return acumulado; }
    protected void setAcumulado(double acumulado) { this.acumulado = acumulado; }
    protected OperacionStrategy getOperacion() { return operacionActual; }
    protected void setOperacion(OperacionStrategy operacionActual) { this.operacionActual = operacionActual; }
}