package patrones.ejercicio12_p;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // 1. Creamos un automóvil con política Flexible
        AutoEnAlquiler miAuto = new AutoEnAlquiler(10000.0, 5, "Toyota", new Flexible());
        
        // 2. Creamos una reserva de 5 días que inicia el 20 de Mayo
        LocalDate fechaInicio = LocalDate.of(2026, 5, 20);
        Reserva miReserva = new Reserva(5, fechaInicio, miAuto);
        
        System.out.println("Monto total a pagar: $" + miReserva.montoAPagar()); // $50000.0
        
        // 3. Simulamos una cancelación el 15 de Mayo (5 días antes)
        LocalDate fechaCancelacion = LocalDate.of(2026, 5, 15);
        
        // --- POLÍTICA FLEXIBLE ---
        double reembolsoFlexible = miReserva.montoAReembolsar(fechaCancelacion);
        System.out.println("Reembolso con política Flexible: $" + reembolsoFlexible);
        
        // 4. Cambiamos la política a Moderada en tiempo de ejecución
        miAuto.setPolitica(new Moderada());
        
        // --- POLÍTICA MODERADA ---
        // Como se cancela con 5 días de anticipación (menos de una semana, pero más de 2 días), 
        // debería devolver el 50%
        double reembolsoModerado = miReserva.montoAReembolsar(fechaCancelacion);
        System.out.println("Reembolso con política Moderada: $" + reembolsoModerado);
    }
}