package patrones.ejercicio10_p;

public class Error extends EstadoCalculadora {
    @Override
    public String getResultado(Calculadora ctx) {
        return "Error";
    }

    @Override
    public void setValor(Calculadora ctx, double unValor) {
        // En error no hace nada
    }
    
    @Override
    public void mas(Calculadora ctx) {} // Ignora
    
    @Override
    public void menos(Calculadora ctx) {} // Ignora
    
    @Override
    public void por(Calculadora ctx) {} // Ignora
    
    @Override
    public void dividido(Calculadora ctx) {} // Ignora
}
