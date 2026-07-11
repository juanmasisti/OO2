* Tarea 1: Justificación del Patrón
Patrón utilizado: State.
Justificación: El comportamiento de la excursión (cómo responde a obtenerInformacion() y cómo procesa las inscripciones en inscribir()) cambia dinámicamente durante su ciclo de vida en función de la cantidad de usuarios inscriptos. El patrón State permite encapsular el comportamiento específico de cada fase (Provisoria, Definitiva, Llena) en clases separadas, eliminando condicionales complejos en la clase Excursion y facilitando la extensión futura

* Tareas 2(res-1-uml.png) y 3: Implementación en Java
Para mantener el código limpio y eficiente, la clase Excursion delega totalmente la responsabilidad. Fíjate cómo los estados se encargan de evaluar si corresponde cambiar al siguiente estado luego de agregar un usuario(y de validar si puede agregar un usuario a la excursión o a la lista de espera).

// --- DOMINIO ---
public class Usuario {
    private String nombre;
    private String apellido;
    private String email;

    public Usuario(String nombre, String apellido, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public String getEmail() { return email; }
}

public class Excursion {
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String puntoEncuentro;
    private double costo;
    private int cupoMin;
    private int cupoMax;
    
    private List<Usuario> inscriptos;
    private List<Usuario> listaEspera;
    private EstadoExcursion estado;

    public Excursion(String nombre, LocalDate fechaInicio, LocalDate fechaFin, String puntoEncuentro, double costo, int cupoMin, int cupoMax) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.puntoEncuentro = puntoEncuentro;
        this.costo = costo;
        this.cupoMin = cupoMin;
        this.cupoMax = cupoMax;
        
        this.inscriptos = new ArrayList<>();
        this.listaEspera = new ArrayList<>();
        this.estado = new Provisoria(); // Estado inicial
    }

    public void inscribir(Usuario unUsuario) {
        this.estado.inscribir(this, unUsuario);
    }

    public String obtenerInformacion() {
        return this.estado.obtenerInformacion(this);
    }

    // Métodos package-private para que el estado interactúe con el contexto
    protected void setEstado(EstadoExcursion nuevoEstado) { this.estado = nuevoEstado; }
    protected void agregarInscripto(Usuario u) { this.inscriptos.add(u); }
    protected void agregarAEspera(Usuario u) { this.listaEspera.add(u); }
    
    // Getters necesarios para la información
    public String obtenerInformacionBase() {
        return "Nombre: " + nombre + ", Costo: " + costo + ", Fechas: " + fechaInicio + " a " + fechaFin + ", Punto de encuentro: " + puntoEncuentro;
    }
    public int getInscriptosSize() { return this.inscriptos.size(); }
    public int getCupoMin() { return this.cupoMin; }
    public int getCupoMax() { return this.cupoMax; }
    public List<Usuario> getInscriptos() { return this.inscriptos; }
    public int getListaEsperaSize() { return this.listaEspera.size(); }

    // Métodos para tener la logica acá y llamar desde los estados
    public boolean alcanzoCupoMinimo() {
    return this.inscriptos.size() >= this.cupoMin;
    }
    public boolean alcanzoCupoMaximo() {
        return this.inscriptos.size() >= this.cupoMax;
    }
    public int faltantesParaMinimo() {
        return this.cupoMin - this.inscriptos.size();
    }
}

// --- JERARQUÍA STATE ---
public abstract class EstadoExcursion {
    public abstract void inscribir(Excursion context, Usuario u);
    public abstract String obtenerInformacion(Excursion context);
}

public class Provisoria extends EstadoExcursion {
    @Override
    public void inscribir(Excursion context, Usuario u) {
        context.agregarInscripto(u);
        if (context.alcanzoCupoMinimo()) {
            context.setEstado(new Definitiva());
        }
    }

    @Override
    public String obtenerInformacion(Excursion context) {
        int faltantes = context.faltantesParaMinimo();
        return context.obtenerInformacionBase() + " | Faltan para mínimo: " + faltantes;
    }
}

public class Definitiva extends EstadoExcursion {
    @Override
    public void inscribir(Excursion context, Usuario u) {
        context.agregarInscripto(u);
        if (context.alcanzoCupoMaximo()) {
            context.setEstado(new Llena());
        }
    }

    @Override
    public String obtenerInformacion(Excursion context) {
        int faltantes = context.getCupoMax() - context.getInscriptosSize();
        String emails = context.getInscriptos().stream()
                .map(Usuario::getEmail)
                .collect(Collectors.joining(", "));
        
        return context.obtenerInformacionBase() + " | Emails: " + emails + " | Faltan para máximo: " + faltantes;
    }
}

public class Llena extends EstadoExcursion {
    @Override
    public void inscribir(Excursion context, Usuario u) {
        context.agregarAEspera(u);
    }

    @Override
    public String obtenerInformacion(Excursion context) {
        return context.obtenerInformacionBase(); // Solo la info base requerida
    }
}

* Tarea 4: Tests Unitarios (JUnit)
El enunciado pide probar específicamente una excursión que ya alcanzó el cupo máximo (cupoMax = 2) y que al anotar a una tercera persona, el comportamiento sea el adecuado para el estado "Llena".

- Código de test:

public class ExcursionTest {
    private Excursion excursionKayak;
    private Usuario u1, u2, u3;

    @BeforeEach
    void setUp() {
        excursionKayak = new Excursion("Dos días en kayak bajando el Paraná", 
            LocalDate.of(2026, 11, 10), LocalDate.of(2026, 11, 12), "Muelle", 5000.0, 1, 2);
            
        u1 = new Usuario("Ana", "Perez", "ana@mail.com");
        u2 = new Usuario("Juan", "Gomez", "juan@mail.com");
        u3 = new Usuario("Luis", "Diaz", "luis@mail.com");
        
        // Inscribimos a dos personas para que la excursión esté Llena
        excursionKayak.inscribir(u1); // Pasa de Provisoria a Definitiva
        excursionKayak.inscribir(u2); // Pasa de Definitiva a Llena
    }

    @Test
    void testInscribirTercerUsuarioVaAListaEspera() {
        // En estado Llena, u3 debería ir a la lista de espera
        excursionKayak.inscribir(u3);
        
        // La lista principal se mantiene en su límite de 2
        assertEquals(2, excursionKayak.getInscriptosSize());
        // Verificamos que la lista de espera creció (Tu corrección fundamental)
        assertEquals(1, excursionKayak.getListaEsperaSize());
    }
}