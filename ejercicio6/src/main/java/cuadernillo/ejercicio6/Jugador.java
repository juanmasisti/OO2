package cuadernillo.ejercicio6;

public class Jugador {
	private String nombre;
    private String apellido;
    private int puntuacion = 0;

    public void incrementarPuntuacion() {
        this.puntuacion += 100;
    }

    public void decrementarPuntuacion() {
        this.puntuacion -= 50;
    }
}
