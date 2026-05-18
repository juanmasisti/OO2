package patrones.ejercicio9_p;

import java.util.List;

public interface SugerenciaStrategy {
	
	List<Pelicula> sugerir(Decodificador contexto);

}
