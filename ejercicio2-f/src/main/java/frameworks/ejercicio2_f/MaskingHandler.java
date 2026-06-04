package frameworks.ejercicio2_f;

import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MaskingHandler extends Handler {
    private Handler wrappee; // El handler que estamos envolviendo (Decorator)
    private List<String> palabrasOcultas;

    public MaskingHandler(Handler wrappee, List<String> palabrasOcultas) {
        this.wrappee = wrappee;
        this.palabrasOcultas = palabrasOcultas;
    }

    @Override
    public void publish(LogRecord record) {
        if (!isLoggable(record)) return;

        String mensajeModificado = record.getMessage();
        
        // Reemplazamos cada palabra prohibida por "***"
        for (String palabra : palabrasOcultas) {
            mensajeModificado = mensajeModificado.replace(palabra, "***");
        }
        
        // Modificamos el record original y delegamos al handler real
        record.setMessage(mensajeModificado);
        this.wrappee.publish(record);
    }

    @Override
    public void flush() {
        this.wrappee.flush();
    }

    @Override
    public void close() throws SecurityException {
        this.wrappee.close();
    }
}
