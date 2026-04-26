package patrones.ejercicio6_p;

public class SinTACCBuilder extends SandwichBuilder {
    @Override public void buildPan() { sandwich.agregarIngrediente(150); }
    @Override public void buildAderezo() { sandwich.agregarIngrediente(18); }
    @Override public void buildPrincipal() { sandwich.agregarIngrediente(250); }
    @Override public void buildAdicional() { sandwich.agregarIngrediente(200); }
}