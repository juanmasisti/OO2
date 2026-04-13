package patrones.ejercicio2_p;

public class Temporario extends Empleado{
	private int horasTrabajadas;
    private boolean estaCasado;
    private int cantidadHijos;

    public Temporario(int horasTrabajadas, boolean estaCasado, int cantidadHijos) {
        this.horasTrabajadas = horasTrabajadas;
        this.estaCasado = estaCasado;
        this.cantidadHijos = cantidadHijos;
        
    }
    
    @Override
    protected double basico() {
        return 20000 + (this.horasTrabajadas * 300);
    }
    
    @Override
    protected double adicional() {
        double total = 0;
        if (this.estaCasado) total += 5000;
        total += (this.cantidadHijos * 2000);
        return total;
    }
}
