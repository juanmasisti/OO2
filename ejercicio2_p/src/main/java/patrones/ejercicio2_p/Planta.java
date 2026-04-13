package patrones.ejercicio2_p;

public class Planta extends Empleado{
	private boolean estaCasado;
	private int cantHijos;
	private int antiguedad;
	
	public Planta(boolean estaCasado, int cantHijos, int antiguedad) {
		this.estaCasado = estaCasado;
		this.cantHijos = cantHijos;
		this.antiguedad = antiguedad;
	}
	
	protected double basico() {
		return 50000;
	}
	
	protected double adicional() {
		double total = 0;
        if (this.estaCasado) total += 5000;
        total += (this.cantHijos * 2000);
        total += (this.antiguedad * 2000);
        return total;	
       
	}

}
