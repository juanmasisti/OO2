package patrones.ejercicio6_p;

public class ClasicoBuilder extends SandwichBuilder {
    @Override public void buildPan() { sandwich.agregarIngrediente(100); }
    @Override public void buildAderezo() { sandwich.agregarIngrediente(20); }
    @Override public void buildPrincipal() { sandwich.agregarIngrediente(300); }
    @Override public void buildAdicional() { sandwich.agregarIngrediente(80); }
}