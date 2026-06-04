package patrones.ejercicio16_p;

import java.util.stream.Collectors;

public abstract class EstadoExcursion {
    public abstract void inscribir(Excursion ctx, Usuario unUsuario);
    public abstract String obtenerInformacion(Excursion ctx);
    
    // Método helper para no repetir el armado de la info básica en todos los estados
    protected String infoBasica(Excursion ctx) {
        return "Nombre: " + ctx.getNombre() + 
               ", Costo: $" + ctx.getCosto() + 
               ", Fechas: " + ctx.getFechas() + 
               ", Punto Encuentro: " + ctx.getPuntoEncuentro();
    }
}
