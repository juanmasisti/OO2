package patrones.ejercicio25_p;

import java.time.LocalDate;

public class Afiliado {
    private String nombre;
    private int familiaresACargo;
    private double salario;
    private LocalDate fechaNacimiento;
    private PlanMedico plan;
    // NACE inicializado con el Null Object. ¡Chau nulls!
    private Coseguro coseguro = new CoseguroNulo();

    public Afiliado(String nombre, int familiaresACargo, double salario, LocalDate fechaNacimiento, PlanMedico plan) {
        this.nombre = nombre;
        this.familiaresACargo = familiaresACargo;
        this.salario = salario;
        this.fechaNacimiento = fechaNacimiento;
        this.plan = plan;
    }

    public void setPlanMedico(PlanMedico plan) { this.plan = plan; }
    // Si contrata un coseguro, usamos el setter (sobreescribe el null por defecto).
    public void setCoseguro(Coseguro coseguro) { 
        this.coseguro = coseguro; 
    }

    public int getFamiliaresACargo() { return familiaresACargo; }
    public double getSalario() { return salario; }
    public Coseguro getCoseguro() { return coseguro; }
    

    // El afiliado delega pasándo sus datos a la estrategia configurada (plan médico) para calcular el monto total a pagar.
    public double calcularMonto() {
        return plan.calcularCostoTotal(this.getSalario(), this.getFamiliaresACargo(), this.getCoseguro());
    }
}