package ejercicio3;

public class CharRing extends Ring{
	private char[] source;

    public CharRing(String src) {
        source = src.toCharArray();
    }

    public char next() {
        return source[calcularSiguienteIndice(source.length)];
    }
}
