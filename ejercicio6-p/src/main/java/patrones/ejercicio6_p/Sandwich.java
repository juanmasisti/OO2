package patrones.ejercicio6_p;

public class Sandwich {
    private double precioTotal = 0;

    public void agregarIngrediente(double precio) {
        this.precioTotal += precio;
    }

    public double getPrecio() {
        return this.precioTotal;
    }
}