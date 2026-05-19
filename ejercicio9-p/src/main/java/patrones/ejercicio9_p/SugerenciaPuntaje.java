package patrones.ejercicio9_p;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SugerenciaPuntaje implements SugerenciaStrategy{
	
	@Override
    public List<Pelicula> sugerir(Decodificador contexto) {
        return contexto.getPeliculasNoReproducidas().stream()
                // Ordena por puntaje DESC, y luego por año DESC para desempatar
                .sorted(Comparator.comparing(Pelicula::getPuntaje).reversed()
                        .thenComparing(Comparator.comparing(Pelicula::getAnio).reversed()))
                .limit(3)
                .collect(Collectors.toList());
    }

}
