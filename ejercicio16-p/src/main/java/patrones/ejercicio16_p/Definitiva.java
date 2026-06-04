package patrones.ejercicio16_p;

import java.util.stream.Collectors;

class Definitiva extends EstadoExcursion {
    @Override
    public void inscribir(Excursion ctx, Usuario unUsuario) {
        ctx.getInscriptos().add(unUsuario);
        
        // Transición de estado automática
        if (ctx.getInscriptos().size() == ctx.getCupoMaximo()) {
            ctx.setEstado(new Llena());
        }
    }

    @Override
    public String obtenerInformacion(Excursion ctx) {
        int faltantes = ctx.getCupoMaximo() - ctx.getInscriptos().size();
        String emails = ctx.getInscriptos().stream()
                           .map(Usuario::getEmail)
                           .collect(Collectors.joining(", "));
                           
        return infoBasica(ctx) + ", Emails: [" + emails + "], Faltantes para máximo: " + faltantes;
    }
}