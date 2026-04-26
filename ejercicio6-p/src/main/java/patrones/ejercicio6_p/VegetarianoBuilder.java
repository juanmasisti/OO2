package patrones.ejercicio6_p;

public class VegetarianoBuilder extends SandwichBuilder {
    @Override public void buildPan() { sandwich.agregarIngrediente(120); }
    
    // El vegetariano no lleva aderezo, el paso se ignora
    @Override public void buildAderezo() { } 
    
    @Override public void buildPrincipal() { sandwich.agregarIngrediente(200); }
    @Override public void buildAdicional() { sandwich.agregarIngrediente(100); }
}
