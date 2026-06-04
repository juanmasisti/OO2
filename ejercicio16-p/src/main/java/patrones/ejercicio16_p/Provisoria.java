package patrones.ejercicio16_p;

class Provisoria extends EstadoExcursion {
    @Override
    public void inscribir(Excursion ctx, Usuario unUsuario) {
        ctx.getInscriptos().add(unUsuario);
        
        // Transición de estado automática
        if (ctx.getInscriptos().size() == ctx.getCupoMinimo()) {
            ctx.setEstado(new Definitiva());
        }
    }

    @Override
    public String obtenerInformacion(Excursion ctx) {
        int faltantes = ctx.getCupoMinimo() - ctx.getInscriptos().size();
        return infoBasica(ctx) + ", Faltantes para mínimo: " + faltantes;
    }
}