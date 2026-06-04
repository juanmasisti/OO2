package frameworks.ejercicio2_f;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class JsonFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        // Armamos la estructura requerida {"message": "...", "level": "..."}
        String message = record.getMessage();
        String level = record.getLevel().getName().toLowerCase(); 
        
        return "{ \"message\": \"" + message + "\", \"level\": \"" + level + "\" }\n";
    }
}
