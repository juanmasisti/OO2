package patrones.ejercicio1_p;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.List;

// El Adapter DEBE implementar la interfaz Target
public class JSONSimpleAdapter implements Exporter {

    @Override
    @SuppressWarnings("unchecked") // Para evitar warnings de la librería antigua
    public String exportar(List<Socio> socios) {
        JSONArray jsonArray = new JSONArray();

        for (Socio socio : socios) {
            JSONObject jsonSocio = new JSONObject();
            // Mapeamos los datos del socio al formato que pide la librería
            jsonSocio.put("nombre", socio.getNombre());
            jsonSocio.put("email", socio.getEmail());
            jsonSocio.put("legajo", socio.getLegajo());
            
            jsonArray.add(jsonSocio);
        }

        // La librería nos da el JSON listo
        return jsonArray.toJSONString();
    }
}