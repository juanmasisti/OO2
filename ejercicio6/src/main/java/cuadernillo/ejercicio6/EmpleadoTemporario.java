package cuadernillo.ejercicio6;

public class EmpleadoTemporario extends Empleado{
	private double horasTrabajadas;
    private int cantidadHijos;

    @Override
    protected double calcularAdicional() {
        return (this.horasTrabajadas * 500) + (this.cantidadHijos * 1000);
    }
}
