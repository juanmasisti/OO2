package refactoring.ejercicio10;

public class LlamadaInternacional extends Llamada {
    public LlamadaInternacional(String origen, String destino, int duracion) {
		super(origen, destino, duracion);
	}

	@Override
    public double calcularCostoBase() {
        return getDuracion() * 150 + (getDuracion() * 150 * 0.21) + 50;
    }
}