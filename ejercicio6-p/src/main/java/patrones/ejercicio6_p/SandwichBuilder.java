package patrones.ejercicio6_p;

public abstract class SandwichBuilder {
    protected Sandwich sandwich;

    public void crearSandwich() {
        this.sandwich = new Sandwich();
    }

    public Sandwich getSandwich() {
        return this.sandwich;
    }

    // Los pasos de construcción que todos deben implementar
    public abstract void buildPan();
    public abstract void buildAderezo();
    public abstract void buildPrincipal();
    public abstract void buildAdicional();
}