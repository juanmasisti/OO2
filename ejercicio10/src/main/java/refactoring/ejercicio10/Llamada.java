package refactoring.ejercicio10;

public abstract class Llamada {
	private String origen;
	private String destino;
	private int duracion;

	
	public Llamada(String origen, String destino, int duracion) {
		this.origen = origen;
		this.destino = destino;
		this.duracion = duracion;
	}
	
	public abstract double calcularCostoBase();

	public String getRemitente() {
		return destino;
	}

	public int getDuracion() {
		return this.duracion;
	}

	public String getOrigen() {
		return origen;
	}
}
