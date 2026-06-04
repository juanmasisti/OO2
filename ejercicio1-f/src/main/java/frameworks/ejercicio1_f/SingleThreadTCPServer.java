package frameworks.ejercicio1_f;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class SingleThreadTCPServer {

    public abstract void handleMessage(String message, PrintWriter out);

    public final void startLoop(String[] args) {
        checkArguments(args);

        int portNumber = Integer.parseInt(args[0]);

    
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            displaySocketInformation(portNumber);
            while (true) {
                Socket clientSocket = acceptAndDisplaySocket(serverSocket);
                handleClient(clientSocket);
            }
        } catch (IOException e) {
            displayAndExit(portNumber);
        }
    }

    protected void displayAndExit(int portNumber) {
        System.err.println("Could not listen on port " + portNumber);
        System.exit(-1);
    }

    protected Socket acceptAndDisplaySocket(ServerSocket serverSocket) throws IOException {
        Socket clientSocket = serverSocket.accept();
        displaySocketData(clientSocket);
        return clientSocket;
    }

    protected void displaySocketData(Socket clientSocket) {
        System.out.println("Client connected from: " + clientSocket.getInetAddress().getHostAddress() + ":"
                + clientSocket.getPort());
    }

    protected void displaySocketInformation(int portNumber) {
        System.out.println(this.getClass().getName() + " server listening on port: " + portNumber);
    }

    protected void checkArguments(String[] args) {
        if (args.length != 1) {
            displayUsage();
            System.exit(1);
        }
    }

    protected void displayUsage() {
        System.err.println("Usage: java"+this.getClass().getName() +"<port number>");
    }

    private final void handleClient(Socket clientSocket) {
        try (
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            // Hook Opcional: Antes de empezar a procesar
            onConnectionOpened(clientSocket, out);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received message: " + inputLine + " from "
                        + clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort());
                
                // Uso de la alternativa 2: Hook para evaluar cierre
                if (isEndConnection(inputLine)) {
                    break;
                }
                handleMessage(inputLine, out);
            }
            System.out.println("Connection closed with " + clientSocket.getInetAddress().getHostAddress() + ":"
                    + clientSocket.getPort());
            
            // Hook Opcional: Al finalizar exitosamente
            onConnectionClosed(clientSocket);

        } catch (IOException e) {
            System.err.println("Problem with communication with client: " + e.getMessage());
            onConnectionError(clientSocket, e); // Hook Opcional de error
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing socket: " + e.getMessage());
            }
        }
    }

    // --- HOOKS OPCIONALES (Comportamiento por defecto) ---
    
    // Condición de cierre configurable (Alternativa 2 elegida)
    protected boolean isEndConnection(String inputLine) {
        return inputLine.equalsIgnoreCase(""); 
    }

    // Hooks vacíos para que las subclases los usen si lo necesitan
    protected void onConnectionOpened(Socket clientSocket, PrintWriter out) {}
    protected void onConnectionClosed(Socket clientSocket) {}
    protected void onConnectionError(Socket clientSocket, Exception e) {}
}
