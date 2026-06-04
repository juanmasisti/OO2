package refactoring.ejercicio10;

public class LlamadaNacional extends Llamada {
	
	
    public LlamadaNacional(String origen, String destino, int duracion) {
		super(origen, destino, duracion);
	}

	@Override
    public double calcularCostoBase() {
        return getDuracion() * 3 + (getDuracion() * 3 * 0.21);
    }
}