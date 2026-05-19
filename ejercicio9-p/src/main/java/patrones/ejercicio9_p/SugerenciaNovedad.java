package patrones.ejercicio9_p;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SugerenciaNovedad implements SugerenciaStrategy{
	
	@Override
    public List<Pelicula> sugerir(Decodificador contexto) {
        return contexto.getPeliculasNoReproducidas().stream()
                .sorted(Comparator.comparing(Pelicula::getAnio).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

}
