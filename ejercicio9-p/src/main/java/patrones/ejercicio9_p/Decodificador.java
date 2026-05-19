package patrones.ejercicio9_p;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Decodificador {
    private List<Pelicula> grilla;
    private List<Pelicula> reproducidas;
    private SugerenciaStrategy sugeridor;

    public Decodificador() {
        this.grilla = new ArrayList<>();
        this.reproducidas = new ArrayList<>();
        this.sugeridor = new SugerenciaNovedad(); // Estrategia por defecto
    }

    public void setSugeridor(SugerenciaStrategy sugeridor) {
        this.sugeridor = sugeridor;
    }

    public void agregarPelicula(Pelicula p) { this.grilla.add(p); }
    public void reproducir(Pelicula p) { this.reproducidas.add(p); }
    
    public List<Pelicula> getReproducidas() { return this.reproducidas; }

    // Helper para que las estrategias trabajen con datos limpios
    public List<Pelicula> getPeliculasNoReproducidas() {
        return grilla.stream()
                .filter(p -> !reproducidas.contains(p))
                .collect(Collectors.toList());
    }

    // Delegación al Strategy
    public List<Pelicula> sugerir() {
        return this.sugeridor.sugerir(this);
    }
}
