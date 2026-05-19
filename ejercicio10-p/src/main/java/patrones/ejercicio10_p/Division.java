package patrones.ejercicio10_p;

public class Division implements OperacionStrategy{
	public double calcular(double a, double b) {
		if (b == 0) throw new ArithmeticException("División por cero");
        return a / b;
	};
}
