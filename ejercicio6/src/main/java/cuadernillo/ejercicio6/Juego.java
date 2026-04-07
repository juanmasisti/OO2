package cuadernillo.ejercicio6;

public class Juego {
	// La clase Juego ahora delega la responsabilidad al Jugador.
    public void incrementar(Jugador j) {
        j.incrementarPuntuacion();
    }

    public void decrementar(Jugador j) {
        j.decrementarPuntuacion();
    }
}
