package refactoring.ejercicio10;

public class PersonaJuridica extends Cliente {
    private String cuit;
    
    public PersonaJuridica(String nombre, String cuit) {
		super(nombre);
		this.cuit = cuit;
	}

	public double aplicarDescuento(double costoBase) { return costoBase * 0.85; } // 15% descuento

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	
	
}
