package patrones.ejercicio16_p;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Excursion {
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String puntoEncuentro;
    private double costo;
    private int cupoMinimo;
    private int cupoMaximo;
    
    private List<Usuario> inscriptos;
    private List<Usuario> listaDeEspera;
    private EstadoExcursion estado;

    public Excursion(String nombre, LocalDate inicio, LocalDate fin, String puntoEncuentro, double costo, int min, int max) {
        this.nombre = nombre;
        this.fechaInicio = inicio;
        this.fechaFin = fin;
        this.puntoEncuentro = puntoEncuentro;
        this.costo = costo;
        this.cupoMinimo = min;
        this.cupoMaximo = max;
        
        this.inscriptos = new ArrayList<>();
        this.listaDeEspera = new ArrayList<>();
        this.estado = new Provisoria(); // Estado inicial
    }

    // --- Delegación al Patrón State ---
    public void inscribir(Usuario unUsuario) {
        estado.inscribir(this, unUsuario);
    }

    public String obtenerInformacion() {
        return estado.obtenerInformacion(this);
    }

    // --- Métodos de visibilidad de paquete/protected para el State ---
    protected void setEstado(EstadoExcursion estado) { this.estado = estado; }
    protected List<Usuario> getInscriptos() { return inscriptos; }
    protected List<Usuario> getListaDeEspera() { return listaDeEspera; }
    
    // Getters para armar la información
    public String getNombre() { return nombre; }
    public double getCosto() { return costo; }
    public String getFechas() { return fechaInicio + " a " + fechaFin; }
    public String getPuntoEncuentro() { return puntoEncuentro; }
    public int getCupoMinimo() { return cupoMinimo; }
    public int getCupoMaximo() { return cupoMaximo; }
}
