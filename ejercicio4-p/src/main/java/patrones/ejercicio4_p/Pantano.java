package patrones.ejercicio4_p;

public class Pantano extends Topografia {
 @Override
 public double proporcionAgua() { return 0.7; }

 @Override
 public boolean equals(Object obj) {
     return obj instanceof Pantano;
 }
}