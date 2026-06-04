package frameworks.ejercicio1_f;

import java.io.PrintWriter;
import java.net.Socket;

public class RepeatServer extends SingleThreadTCPServer {

    @Override
    public void handleMessage(String message, PrintWriter out) {
        // Separamos considerando que puede haber múltiples espacios
        String[] args = message.trim().split("\\s+");
        
        if (args.length < 2) {
            out.println("Error: Se requieren al menos 2 argumentos (string y cantidad).");
            return;
        }

        String strToRepeat = args[0];
        
        if (strToRepeat.isEmpty()) {
            out.println("Error: El string a repetir no puede ser nulo o vacio.");
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(args[1]);
            if (cantidad <= 0) {
                out.println("Error: La cantidad de veces debe ser un entero mayor a 0.");
                return;
            }
        } catch (NumberFormatException e) {
            out.println("Error: El argumento de cantidad ('" + args[1] + "') no es un numero valido.");
            return;
        }

        // Si existe arg 3, es el delimitador. Si no, usamos " " por defecto.
        String delimiter = (args.length >= 3) ? args[2] : " ";

        // Generamos el string repetido
        String result = (strToRepeat + delimiter).repeat(cantidad);
        
        // Removemos el último delimitador que queda colgando al final
        result = result.substring(0, result.length() - delimiter.length());

        out.println(result);
    }

    // Usamos el hook opcional para saludar al cliente al conectar
    @Override
    protected void onConnectionOpened(Socket clientSocket, PrintWriter out) {
        out.println("Bienvenido al RepeatServer. Envie: <string> <cantidad> [delimitador]");
    }

    public static void main(String[] args) {
        new RepeatServer().startLoop(args);
    }
}