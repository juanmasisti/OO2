package patrones.ejercicio6_p;

public class Director {
    private SandwichBuilder builder;

    public Director(SandwichBuilder builder) {
        this.builder = builder;
    }

    public void setBuilder(SandwichBuilder builder) {
        this.builder = builder;
    }

    // El Director conoce "la receta" (el orden de los pasos)
    // pero ignora qué ingredientes exactos se están usando.
    public void armarSandwich() {
        this.builder.crearSandwich();
        this.builder.buildPan();
        this.builder.buildAderezo();
        this.builder.buildPrincipal();
        this.builder.buildAdicional();
    }

    public Sandwich getSandwich() {
        return this.builder.getSandwich();
    }
}