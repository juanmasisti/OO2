package refactoring.ejercicio10;

import java.util.SortedSet;

public class GeneradorPrimero implements GeneradorStrategy {
    public String obtenerNumero(SortedSet<String> lineas) {
        String linea = lineas.first();
        lineas.remove(linea);
        return linea;
    }
}

