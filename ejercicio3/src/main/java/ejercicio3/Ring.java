package ejercicio3;

public abstract class Ring {
	protected int idx = 0;
	
	protected int calcularSiguienteIndice(int limite) {
        if (idx >= limite)
            idx = 0;
        return idx++;
    }
}
