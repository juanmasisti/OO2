package frameworks.ejercicio2_f;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class UpperCaseFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        // Extraemos el mensaje, lo pasamos a mayúsculas y le agregamos un salto de línea
        return record.getMessage().toUpperCase() + "\n";
    }
}