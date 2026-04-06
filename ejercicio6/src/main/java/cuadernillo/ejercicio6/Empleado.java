package cuadernillo.ejercicio6;

public abstract  class Empleado {
	    private String nombre;
	    private String apellido;
	    protected double sueldoBasico; // Protected para que las subclases puedan inicializarlo

	    // Template Method
	    public final double sueldo() {
	        return this.sueldoBasico + this.calcularAdicional() - this.calcularDescuento();
	    }

	    // Métodos que las subclases deben implementar
	    protected abstract double calcularAdicional();

	    protected double calcularDescuento() {
	        return this.sueldoBasico * 0.13; // Descuento común
	    }
}

