package patrones.ejercicio1_p;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class JacksonAdapter implements Exporter {

    @Override
    public String exportar(List<Socio> socios) {
        // ObjectMapper es el "Adaptee" en este caso
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Jackson hace el mapeo automáticamente leyendo los getters de la lista!
            return mapper.writeValueAsString(socios);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "[]"; // Retorno por defecto en caso de error
        }
    }
}