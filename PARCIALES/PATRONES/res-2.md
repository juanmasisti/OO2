* Tarea 1: Diseño UML y Justificación del Patrón
Patrón utilizado: State.
Justificación: El sistema presenta una clase Proyecto cuyo comportamiento frente a los mensajes aprobarEtapa(), modificarMargenDeGanancia() y cancelar() cambia dinámicamente dependiendo de la fase en la que se encuentre. El patrón State permite aislar las reglas de negocio de cada etapa en clases concretas (EnConstruccion, EnEvaluacion, Confirmada, Cancelado), eliminando la necesidad de múltiples sentencias condicionales (if/switch) y delegando la responsabilidad de las transiciones al estado correspondiente.

* Tarea 2: Implementación en Java
Para implementar esto de forma elegante, vamos a usar una Clase Abstracta (EstadoProyecto) en lugar de una interfaz. Esto nos permite definir métodos vacíos por defecto para cumplir con la regla de "En otra situación: No produce efecto alguno", evitando tener código repetido en los estados que no hacen nada.

- Código:

// --- DOMINIO (CONTEXTO) ---
public class Proyecto {
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String objetivo;
    private int integrantes;
    private double montoPorDia;
    private double margenGanancia;
    private EstadoProyecto estado;

    // Al crear el proyecto arranca en etapa "En construcción" (7% de margen = 0.07)
    public Proyecto(String nombre, LocalDate fechaInicio, LocalDate fechaFin, String objetivo, int integrantes, double montoPorDia) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.objetivo = objetivo;
        this.integrantes = integrantes;
        this.montoPorDia = montoPorDia;
        this.margenGanancia = 0.07; 
        this.estado = new EnConstruccion();
    }

    // --- MÉTODOS DELEGADOS AL ESTADO ---
    public void aprobarEtapa() {
        this.estado.aprobarEtapa(this);
    }

    public void modificarMargenGanancia(double nuevoMargen) {
        this.estado.modificarMargenGanancia(this, nuevoMargen);
    }

    public void cancelar() {
        this.estado.cancelar(this);
    }

    // --- MÉTODOS PROPIOS (Experto en Información) ---
    // Aplican en cualquier etapa, NO se delegan al estado.
    public double costoDelProyecto() {
        long diasDeTrabajo = ChronoUnit.DAYS.between(this.fechaInicio, this.fechaFin);
        return this.integrantes * this.montoPorDia * diasDeTrabajo;
    }

    public double precioDelProyecto() {
        return this.costoDelProyecto() * (1 + this.margenGanancia);
    }

    // --- MÉTODOS PACKAGE-PRIVATE PARA EL ESTADO ---
    void setEstado(EstadoProyecto nuevoEstado) { this.estado = nuevoEstado; }
    void setMargenGanancia(double margen) { this.margenGanancia = margen; }
    void setObjetivo(String nuevoObjetivo) { this.objetivo = nuevoObjetivo; }
    
    // Getters necesarios
    public String getObjetivo() { return this.objetivo; }
    public EstadoProyecto getEstado() { return this.estado; }
}

// --- JERARQUÍA DE ESTADOS ---
public abstract class EstadoProyecto {
    // Por defecto, los métodos no producen efecto (vacíos)
    public void aprobarEtapa(Proyecto p) {}
    
    public void modificarMargenGanancia(Proyecto p, double margen) {}
    
    // Comportamiento por defecto para cancelar (aplica a todos menos al Cancelado)
    public void cancelar(Proyecto p) {
        p.setObjetivo(p.getObjetivo() + " (Cancelado)");
        p.setEstado(new Cancelado());
    }
}

public class EnConstruccion extends EstadoProyecto {
    @Override
    public void aprobarEtapa(Proyecto p) {
        if (p.precioDelProyecto() == 0) {
            throw new RuntimeException("Error en aprobación: El precio del proyecto es cero.");
        }
        p.setEstado(new EnEvaluacion());
    }

    @Override
    public void modificarMargenGanancia(Proyecto p, double margen) {
        if (margen >= 0.08 && margen <= 0.10) {
            p.setMargenGanancia(margen);
        }
    }
}

public class EnEvaluacion extends EstadoProyecto {
    @Override
    public void aprobarEtapa(Proyecto p) {
        p.setEstado(new Confirmada());
    }

    @Override
    public void modificarMargenGanancia(Proyecto p, double margen) {
        if (margen >= 0.11 && margen <= 0.15) {
            p.setMargenGanancia(margen);
        }
    }
}

public class Confirmada extends EstadoProyecto {
    // No sobrescribe nada específico, usa los defaults de la clase abstracta
    // Aprobar y Modificar no hacen nada. Cancelar le agrega la etiqueta.
}

public class Cancelado extends EstadoProyecto {
    // Sobrescribe el cancelar para que no haga efecto si ya estaba cancelado
    @Override
    public void cancelar(Proyecto p) {
        // No produce efecto alguno.
    }
}

* Tarea 3: Test Unitario (JUnit 5)
El enunciado pide probar la aprobación de un proyecto con características muy específicas (nombre: "Vacaciones de invierno", objetivo: "salir con amigos", integrantes: 3) que ya se encuentra en evaluación.

Para que el test sea riguroso, primero creamos el proyecto, forzamos que sea válido (para que su costo no sea 0), lo pasamos a evaluación y luego ejecutamos el test real para ver si pasa a Confirmada.

- Código de test:
public class ProyectoTest {
    
    private Proyecto proyectoVacaciones;

    @BeforeEach
    void setUp() {
        // (ii) Nombre: "Vacaciones de invierno" 
        // (iii) Objetivo: "salir con amigos" 
        // (iv) Integrantes: 3
        proyectoVacaciones = new Proyecto(
            "Vacaciones de invierno", 
            LocalDate.of(2026, 7, 10), 
            LocalDate.of(2026, 7, 20), // 10 días de duración
            "salir con amigos", 
            3, 
            5000.0 // Monto por día válido para que el precio no sea 0
        );

        // (i) Se encuentra en evaluación: 
        // Lo aprobamos una vez para sacarlo de "En Construcción"
        proyectoVacaciones.aprobarEtapa();
    }

    @Test
    void testAprobarProyectoEnEvaluacionPasaAConfirmada() {
        // Verificamos el estado inicial antes de la acción
        assertTrue(proyectoVacaciones.getEstado() instanceof EnEvaluacion);
        
        // Ejecutamos la acción a testear
        proyectoVacaciones.aprobarEtapa();
        
        // Verificamos que transicionó correctamente a "Confirmada"
        assertTrue(proyectoVacaciones.getEstado() instanceof Confirmada);
    }
}