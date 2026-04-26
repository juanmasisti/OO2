package patrones.ejercicio4_p;

public abstract class Topografia {
	public abstract double proporcionAgua();
    
    // Método común para todos: la tierra es lo que sobra del agua
    public double proporcionTierra() {
        return 1.0 - this.proporcionAgua();
    }
}
