package patrones.ejercicio2_p;

public abstract class Empleado {
	// Template Method: Define el algoritmo. Usar final?
    public final double sueldo() {
        return this.basico() + this.adicional() - this.descuento();
    }

    // El descuento es igual para todos, usamos los métodos de la propia clase
    protected double descuento() {
        return (this.basico() * 0.13) + (this.adicional() * 0.05);
    }

    // Métodos que las subclases DEBEN implementar
    protected abstract double basico();
    protected abstract double adicional();
}
