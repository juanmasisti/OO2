package refactoring.ejercicio10;

public class PersonaFisica extends Cliente {
    private String dni;
    
    public PersonaFisica(String nombre, String dni) {
		super(nombre);
		this.dni = dni;
	}

	public double aplicarDescuento(double costoBase) { return costoBase; } // 0% descuento

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	
}
