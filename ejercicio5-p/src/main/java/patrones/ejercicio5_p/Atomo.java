package patrones.ejercicio5_p;

public class Atomo extends ElementoQuimico {
    private String nombre;
    private String simbolo;
    private int peso;
    private int carga;
    private boolean esMetal;

    public Atomo(String nombre, String simbolo, int peso, int carga, boolean esMetal) {
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.peso = peso;
        this.carga = carga;
        this.esMetal = esMetal;
    }

    @Override
    public String formula() { return this.simbolo; }

    @Override
    public int pesoMolecular() { return this.peso; }

    @Override
    public int carga() { return this.carga; }

    @Override
    public boolean esValida() { return true; } // Los átomos siempre son válidos

    @Override
    protected boolean tieneMetal() { return this.esMetal; }

    @Override
    protected boolean requiereParentesis() { return false; } 
}