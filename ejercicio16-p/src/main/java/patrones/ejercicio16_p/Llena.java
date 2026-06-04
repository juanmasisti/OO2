package patrones.ejercicio16_p;

class Llena extends EstadoExcursion {
    @Override
    public void inscribir(Excursion ctx, Usuario unUsuario) {
        ctx.getListaDeEspera().add(unUsuario);
        // Ya no hay más transiciones de estado hacia adelante
    }

    @Override
    public String obtenerInformacion(Excursion ctx) {
        return infoBasica(ctx);
    }
}