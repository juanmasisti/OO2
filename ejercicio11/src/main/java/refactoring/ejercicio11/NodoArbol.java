package refactoring.ejercicio11;

public class NodoArbol implements ArbolBinario {
    private int valor;
    private ArbolBinario hijoIzquierdo;
    private ArbolBinario hijoDerecho;

    public NodoArbol(int valor) {
        this.valor = valor;
        // Inicializamos siempre con el Null Object, erradicando el null
        this.hijoIzquierdo = ArbolVacio.getInstance();
        this.hijoDerecho = ArbolVacio.getInstance();
    }

    public int getValor() { return valor; }
    public void setValor(int valor) { this.valor = valor; }

    public ArbolBinario getHijoIzquierdo() { return hijoIzquierdo; }
    public void setHijoIzquierdo(ArbolBinario hijoIzquierdo) { 
        this.hijoIzquierdo = hijoIzquierdo; 
    }

    public ArbolBinario getHijoDerecho() { return hijoDerecho; }
    public void setDerecha(ArbolBinario hijoDerecho) { 
        this.hijoDerecho = hijoDerecho; 
    }

    // Los métodos quedan limpios, sin ifs
    public String recorrerPreorden() {
        return valor + " - " + 
               hijoIzquierdo.recorrerPreorden() + 
               hijoDerecho.recorrerPreorden();
    }

    public String recorrerInorden() {
        return hijoIzquierdo.recorrerInorden() + 
               valor + " - " + 
               hijoDerecho.recorrerInorden();
    }

    public String recorrerPostorden() {
        return hijoIzquierdo.recorrerPostorden() + 
               hijoDerecho.recorrerPostorden() + 
               valor + " - ";
    }

}
