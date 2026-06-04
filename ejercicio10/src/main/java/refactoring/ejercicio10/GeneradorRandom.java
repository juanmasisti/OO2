package refactoring.ejercicio10;

import java.util.ArrayList;
import java.util.Random;
import java.util.SortedSet;

public class GeneradorRandom implements GeneradorStrategy {
    public String obtenerNumero(SortedSet<String> lineas) {
    	String linea = new ArrayList<String>(lineas)
				.get(new Random().nextInt(lineas.size()));
        lineas.remove(linea);
        return linea;
    }
}

