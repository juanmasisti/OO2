package patrones.ejercicio6_p;

public class VeganoBuilder extends SandwichBuilder {
    @Override public void buildPan() { sandwich.agregarIngrediente(100); }
    @Override public void buildAderezo() { sandwich.agregarIngrediente(20); }
    @Override public void buildPrincipal() { sandwich.agregarIngrediente(500); }
    
    // El vegano no lleva adicional
    @Override public void buildAdicional() { }
}