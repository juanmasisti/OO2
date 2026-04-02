package ejercicio3;

public class IntRing extends Ring{
	private int[] source;

	public IntRing(int[] src) {
	     source = src;
	}

	 public int next() {
	     return source[calcularSiguienteIndice(source.length)];
	 }
}
