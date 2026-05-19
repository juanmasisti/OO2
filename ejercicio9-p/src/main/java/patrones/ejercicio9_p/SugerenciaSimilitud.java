package patrones.ejercicio9_p;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SugerenciaSimilitud implements SugerenciaStrategy{
	
	@Override
    public List<Pelicula> sugerir(Decodificador contexto) {
        // Obtenemos todas las similares a las reproducidas
        List<Pelicula> similaresHistoricas = contexto.getReproducidas().stream()
                .flatMap(p -> p.getSimilares().stream())
                .distinct()
                .collect(Collectors.toList());

        return similaresHistoricas.stream()
                .filter(p -> contexto.getPeliculasNoReproducidas().contains(p))
                .sorted(Comparator.comparing(Pelicula::getAnio).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

}
