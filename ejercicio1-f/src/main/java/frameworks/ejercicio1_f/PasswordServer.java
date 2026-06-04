package frameworks.ejercicio1_f;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PasswordServer extends SingleThreadTCPServer {

    @Override
    public void handleMessage(String message, PrintWriter out) {
        String[] args = message.trim().split("\\s+");
        
        if (args.length < 3) {
            out.println("Error: Faltan argumentos. Uso: <letras> <numeros> <especiales>");
            return;
        }

        String letras = args[0];
        String numeros = args[1];
        String especiales = args[2];

        if (letras.isEmpty() || numeros.isEmpty() || especiales.isEmpty()) {
            out.println("Error: Ningun argumento puede estar vacio.");
            return;
        }

        try {
            String password = generarPassword(letras, numeros, especiales);
            out.println("Password generada: " + password);
        } catch (Exception e) {
            out.println("Error al generar password: " + e.getMessage());
        }
    }

    private String generarPassword(String letras, String numeros, String especiales) {
        Random random = new Random();
        List<Character> passChars = new ArrayList<>();

        // 1. Agregar un número (obligatorio)
        passChars.add(numeros.charAt(random.nextInt(numeros.length())));
        // 2. Agregar un carácter especial (obligatorio)
        passChars.add(especiales.charAt(random.nextInt(especiales.length())));
        
        // 3. Rellenar los 6 caracteres restantes con letras
        for (int i = 0; i < 6; i++) {
            passChars.add(letras.charAt(random.nextInt(letras.length())));
        }

        // 4. Mezclar la lista para que el patrón no sea siempre el mismo
        Collections.shuffle(passChars);

        // 5. Convertir a String
        StringBuilder sb = new StringBuilder();
        for (char c : passChars) {
            sb.append(c);
        }
        return sb.toString();
    }

    // Sobrescribimos el Hook para usar una palabra de cierre personalizada
    @Override
    protected boolean isEndConnection(String inputLine) {
        return inputLine.equalsIgnoreCase("exit") || inputLine.equalsIgnoreCase("quit");
    }

    public static void main(String[] args) {
        new PasswordServer().startLoop(args);
    }
}