package patrones.ejercicio5_p;

public abstract class ElementoQuimico {
    public abstract String formula();
    public abstract int pesoMolecular();
    public abstract int carga();
    public abstract boolean esValida();
    
    // Métodos de ayuda internos para la Union
    protected abstract boolean tieneMetal();
    protected abstract boolean requiereParentesis();
    
    // Bonus: Lógica de estabilidad 
    public boolean esMolecula() {
        return this.carga() == 0;
    }
    
    public boolean esIon() {
        return !this.esMolecula();
    }
}