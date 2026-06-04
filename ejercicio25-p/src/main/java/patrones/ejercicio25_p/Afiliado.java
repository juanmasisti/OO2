package patrones.ejercicio25_p;

import java.time.LocalDate;

public class Afiliado {
    private String nombre;
    private int familiaresACargo;
    private double salario;
    private LocalDate fechaNacimiento;
    private PlanMedico plan;
    private Coseguro coseguro; // Puede ser null

    public Afiliado(String nombre, int familiaresACargo, double salario, LocalDate fechaNacimiento, PlanMedico plan) {
        this.nombre = nombre;
        this.familiaresACargo = familiaresACargo;
        this.salario = salario;
        this.fechaNacimiento = fechaNacimiento;
        this.plan = plan;
    }

    public void setPlanMedico(PlanMedico plan) { this.plan = plan; }
    public void setCoseguro(Coseguro coseguro) { this.coseguro = coseguro; }

    public int getFamiliaresACargo() { return familiaresACargo; }
    public double getSalario() { return salario; }
    public boolean tieneCoseguro() { return this.coseguro != null; }
    public Coseguro getCoseguro() { return coseguro; }

    // El afiliado delega pasándose a sí mismo como contexto
    public double calcularMonto() {
        return plan.calcularCostoTotal(this);
    }
}