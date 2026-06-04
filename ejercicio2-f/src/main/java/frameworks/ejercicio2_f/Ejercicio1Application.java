package frameworks.ejercicio2_f;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

public class Ejercicio1Application {

	public static void main(String[] args) {
		configurarLoggers();

		SwingUtilities.invokeLater(() -> new WallPostUI());
	}

	private static void configurarLoggers() {
		try {
			// --- 1. CONFIGURACIÓN DEL MODELO ---
			Logger modelLogger = Logger.getLogger(WallPostImpl.class.getName());
			modelLogger.setUseParentHandlers(false);

			// A. Creamos el FileHandler base y le ponemos nuestro nuevo JsonFormatter
			FileHandler fileHandler = new FileHandler("wallpost_modelo.txt", true);
			fileHandler.setFormatter(new JsonFormatter());

			// B. Envolvemos el FileHandler con nuestro MaskingHandler (Decorator)
			// Censuramos la palabra "publicación" por ejemplo.
			MaskingHandler maskingHandler = new MaskingHandler(
                fileHandler, 
                Arrays.asList("publicación", "likes")
            );
			modelLogger.addHandler(maskingHandler);


			// --- 2. CONFIGURACIÓN DE LA UI ---
			Logger uiLogger = Logger.getLogger(WallPostUI.class.getName());
			uiLogger.setUseParentHandlers(false);

			// A. Agregamos impresión en consola pero en MAYÚSCULAS
			ConsoleHandler consoleHandler = new ConsoleHandler();
			consoleHandler.setFormatter(new UpperCaseFormatter());
			uiLogger.addHandler(consoleHandler);

			// B. Agregamos envío de Emails para la UI usando formato JSON
			EmailHandler emailHandler = new EmailHandler("admin@empresa.com");
			emailHandler.setFormatter(new JsonFormatter());
			uiLogger.addHandler(emailHandler);

		} catch (SecurityException | IOException e) {
			System.err.println("Error fatal al configurar los loggers: " + e.getMessage());
		}
	}
}