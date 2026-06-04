package refactoring.ejercicio10;

import java.util.SortedSet;

public class GeneradorUltimo implements GeneradorStrategy {
    public String obtenerNumero(SortedSet<String> lineas) {
        String linea = lineas.last();
        lineas.remove(linea);
        return linea;
    }
}
