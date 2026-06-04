package refactoring.ejercicio10;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
	private List<Cliente> clientes = new ArrayList<Cliente>();
	private GestorNumerosDisponibles guia = new GestorNumerosDisponibles();

	public boolean agregarNumeroTelefono(String str) {
		return guia.getLineas().add(str);
	}

	public String obtenerNumeroLibre() {
		return guia.obtenerNumeroLibre();
	}

	public Cliente registrarUsuario(Cliente c) {
		clientes.add(c);
		return c;
	}

	public Llamada registrarLlamada(Cliente origen, Llamada llamada) {
	    origen.agregarLlamada(llamada);
	    return llamada;
	}

	public double calcularMontoTotalLlamadas(Cliente cliente) {
		double total = 0;
	    for (Llamada l : cliente.getLlamadas()) {
	        total += cliente.aplicarDescuento(l.calcularCostoBase());
	    }
	    return total;
	}

	public int cantidadDeUsuarios() {
		return clientes.size();
	}

	public boolean existeUsuario(Cliente persona) {
		return clientes.contains(persona);
	}

	public GestorNumerosDisponibles getGestorNumeros() {
		return this.guia;
	}
}
