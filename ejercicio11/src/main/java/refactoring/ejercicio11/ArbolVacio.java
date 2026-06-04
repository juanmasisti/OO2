package refactoring.ejercicio11;

public class ArbolVacio implements ArbolBinario{
	private static ArbolVacio instancia = new ArbolVacio();
	
	private ArbolVacio() {}
    
    public static ArbolVacio getInstance() {
        return instancia;
    }

    public String recorrerPreorden() { return ""; }

    public String recorrerInorden() { return ""; }

    public String recorrerPostorden() { return ""; }

}
