package refactoring.ejercicio11;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArbolBinarioTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	  @Test
	    public void testUnSoloNodo() {
	        NodoArbol arbol = new NodoArbol(10);
	        assertEquals("10 - ", arbol.recorrerPreorden());
	        assertEquals("10 - ", arbol.recorrerInorden());
	        assertEquals("10 - ", arbol.recorrerPostorden());
	    }

	    @Test
	    public void testSoloHijoIzquierdo() {
	    	NodoArbol arbol = new NodoArbol(10);
	        arbol.setHijoIzquierdo(new NodoArbol(5));
	        assertEquals("10 - 5 - ", arbol.recorrerPreorden());
	        assertEquals("5 - 10 - ", arbol.recorrerInorden());
	        assertEquals("5 - 10 - ", arbol.recorrerPostorden());
	    }

	    @Test
	    public void testSoloHijoDerecho() {
	    	NodoArbol arbol = new NodoArbol(10);
	        arbol.setDerecha(new NodoArbol(15));
	        assertEquals("10 - 15 - ", arbol.recorrerPreorden());
	        assertEquals("10 - 15 - ", arbol.recorrerInorden());
	        assertEquals("15 - 10 - ", arbol.recorrerPostorden());
	    }

	    @Test
	    public void testArbolCompletoTresNodos() {
	    	NodoArbol arbol = new NodoArbol(10);
	        arbol.setHijoIzquierdo(new NodoArbol(5));
	        arbol.setDerecha(new NodoArbol(15));
	        assertEquals("10 - 5 - 15 - ", arbol.recorrerPreorden());
	        assertEquals("5 - 10 - 15 - ", arbol.recorrerInorden());
	        assertEquals("5 - 15 - 10 - ", arbol.recorrerPostorden());
	    }

	    @Test
	    public void testArbolConVariosNiveles() {
	    	NodoArbol arbol = new NodoArbol(10);
	    	NodoArbol n5 = new NodoArbol(5);
	    	NodoArbol n15 = new NodoArbol(15);
	    	NodoArbol n3 = new NodoArbol(3);
	    	NodoArbol n7 = new NodoArbol(7);
	    	NodoArbol n12 = new NodoArbol(12);
	    	NodoArbol n18 = new NodoArbol(18);

	        arbol.setHijoIzquierdo(n5);
	        arbol.setDerecha(n15);
	        n5.setHijoIzquierdo(n3);
	        n5.setDerecha(n7);
	        n15.setHijoIzquierdo(n12);
	        n15.setDerecha(n18);

	        assertEquals("10 - 5 - 3 - 7 - 15 - 12 - 18 - ", arbol.recorrerPreorden());
	        assertEquals("3 - 5 - 7 - 10 - 12 - 15 - 18 - ", arbol.recorrerInorden());
	        assertEquals("3 - 7 - 5 - 12 - 18 - 15 - 10 - ", arbol.recorrerPostorden());
	    }
	}
