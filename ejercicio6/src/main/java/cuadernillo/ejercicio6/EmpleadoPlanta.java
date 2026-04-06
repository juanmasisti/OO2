package cuadernillo.ejercicio6;

public class EmpleadoPlanta extends Empleado{
	private int cantidadHijos;

    @Override
    protected double calcularAdicional() {
        return this.cantidadHijos * 2000;
    }

}
