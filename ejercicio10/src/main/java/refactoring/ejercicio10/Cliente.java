package refactoring.ejercicio10;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
	private List<Llamada> llamadas = new ArrayList<Llamada>();
	private String nombre;
	private String numeroTelefono;
	
	public Cliente(String nombre) {
		this.nombre = nombre;
	}

	public abstract double aplicarDescuento(double costoBase);

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	
	public void agregarLlamada(Llamada l) { this.llamadas.add(l); }
	public List<Llamada> getLlamadas() { return new ArrayList<>(this.llamadas); } // Copia segura

}
