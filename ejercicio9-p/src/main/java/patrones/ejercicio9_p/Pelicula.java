package patrones.ejercicio9_p;

import java.util.ArrayList;
import java.util.List;

public class Pelicula {
    private String titulo;
    private double puntaje;
    private int anio;
    private List<Pelicula> similares;

    public Pelicula(String titulo, double puntaje, int anio) {
        this.titulo = titulo;
        this.puntaje = puntaje;
        this.anio = anio;
        this.similares = new ArrayList<>();
    }

    // Establece la relación recíproca de similaridad
    public void registrarSimilaridad(Pelicula otraPelicula) {
        if (!this.similares.contains(otraPelicula)) {
            this.similares.add(otraPelicula);
            otraPelicula.registrarSimilaridad(this); // Recíproco
        }
    }

    public String getTitulo() { return titulo; }
    public double getPuntaje() { return puntaje; }
    public int getAnio() { return anio; }
    public List<Pelicula> getSimilares() { return similares; }
}