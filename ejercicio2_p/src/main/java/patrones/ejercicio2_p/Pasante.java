package patrones.ejercicio2_p;

public class Pasante extends Empleado{
	private int examenesRendidos;
	
	public Pasante(int examenesRendidos) {
		this.examenesRendidos = examenesRendidos;
	}
	 
	@Override
	protected double basico() {
		return 20000;
	}
	
	@Override
	protected double adicional() {
		return this.examenesRendidos * 2000;
	}
	
	
}
