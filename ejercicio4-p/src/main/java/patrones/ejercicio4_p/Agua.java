package patrones.ejercicio4_p;

public class Agua extends Topografia {
    @Override
    public double proporcionAgua() { return 1.0; }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Agua;
    }
}



