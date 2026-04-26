package patrones.ejercicio4_p;

public class Tierra extends Topografia {
    @Override
    public double proporcionAgua() { return 0.0; }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Tierra;
    }
}