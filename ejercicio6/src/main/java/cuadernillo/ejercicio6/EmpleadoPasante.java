package cuadernillo.ejercicio6;

public class EmpleadoPasante extends Empleado{
	
	@Override
	//Este tipo de empleado no tiene adicionales.
	protected double calcularAdicional() {
		return 0;
	}

}
