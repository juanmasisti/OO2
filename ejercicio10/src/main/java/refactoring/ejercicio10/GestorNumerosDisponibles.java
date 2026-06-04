package refactoring.ejercicio10;

import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Random;
import java.util.SortedSet;

public class GestorNumerosDisponibles {
	private SortedSet<String> lineas = new TreeSet<String>();
	private GeneradorStrategy estrategia = new GeneradorUltimo();

	public SortedSet<String> getLineas() {
		return lineas;
	}

	public String obtenerNumeroLibre() {
		return estrategia.obtenerNumero(this.lineas);
	}

	public void cambiarTipoGenerador(GeneradorStrategy nuevaEstrategia) {
		this.estrategia = nuevaEstrategia;
	}
}
