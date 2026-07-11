# Ejercicio 1.1: El Módem opcional
El Problema: El método updateStatus() del Hub asume que el módem siempre existe y llama a sus métodos (attachToNetwork(), sendSMS(), etc.). Nos piden que el módem deje de ser parte del hardware en algunos productos para ahorrar costos, pero no podemos modificar el código de updateStatus().

- a) y b) Patrón y Justificación:

Patrón utilizado: Null Object (Objeto Nulo).

Justificación: Se utiliza para proporcionar un comportamiento por defecto (no hacer nada) cuando una dependencia opcional no está presente. Esto evita tener que modificar el método updateStatus() del cliente (el Hub) llenándolo de condicionales if (modem != null), manteniendo el código limpio y respetando el polimorfismo.

UML Esperado:

* Transformar Modem en una interfaz o clase abstracta (el <<AbstractComponent>>).
* Crear una clase ModemCelular (el módem real) que implemente esa interfaz.
* Crear una clase NullModem (el <<NullObject>>) que implemente la misma interfaz pero con métodos vacíos.
* El Hub mantiene su asociación dirigida hacia la interfaz Modem.

- c) Código Java:

// 1. Extraemos la interfaz
public interface Modem {
    void attachToNetwork();
    void sendSMS(String text);
    void detachFromNetwork();
}

// 2. El módem real (comportamiento original)
public class ModemCelular implements Modem {
    private String phoneNumber;
    private String apn;
    
    @Override
    public void attachToNetwork() { /* Lógica de conexión */ }
    @Override
    public void sendSMS(String text) { /* Lógica de envío */ }
    @Override
    public void detachFromNetwork() { /* Lógica de desconexión */ }
}

// 3. El Null Object (comportamiento neutro para el modelo barato)
public class NullModem implements Modem {
    @Override
    public void attachToNetwork() { // No hace nada }
    @Override
    public void sendSMS(String text) { // No hace nada }
    @Override
    public void detachFromNetwork() { // No hace nada }
}
Para configurar el modelo económico, el sistema simplemente hace hub.setModem(new NullModem()); y el updateStatus() sigue funcionando sin modificar.

# Ejercicio 1.2: El nuevo sensor (Botón de Pánico)
El Problema: Hay que integrar un sensor de terceros (PanicButton) que tiene firmas distintas y no se puede modificar.

a) y b) Patrón y Justificación:

Patrón utilizado: Adapter (Adaptador).

Justificación: Se utiliza para convertir la interfaz de la clase provista por terceros (PanicButton) en la interfaz esperada por nuestro sistema (Sensor). Esto permite que el Hub interactúe con el nuevo botón de pánico de forma polimórfica junto con el resto de los sensores, sin necesidad de modificar el código original del tercero ni del cliente.

UML Esperado:

* Sensor es la interfaz/clase abstracta (<<Target>>).
* PanicButton es la clase del tercero (<<Adaptee>>).
* PanicButtonAdapter hereda/implementa de Sensor (<<Adapter>>) y tiene una flecha de asociación hacia PanicButton (wrapper).

c) Código Java:

public class PanicButtonAdapter extends Sensor {
    private PanicButton adaptee;

    public PanicButtonAdapter(PanicButton adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void reset() {
        this.adaptee.recycleState();
    }

    @Override
    public boolean isActive() {
        return this.adaptee.pollStatus();
    }

    @Override
    public boolean shouldTriggerAlarm() {
        return this.adaptee.panicState();
    }
}

# Ejercicio 2: Malos Olores y Refactoring

a) Code Smells identificados:

Feature Envy (Envidia de características): Líneas 7 a 10. La clase SistemaControlTemperatura está más interesada en los datos internos de Habitacion (tNow, tMin, tMax) que la propia habitación. Está pidiendo los datos para hacer la lógica matemática afuera.

Message Chains (Cadenas de mensajes): Línea 7 (i.habitaciones()). El sistema le está pidiendo al inmueble su colección interna de habitaciones, rompiendo el encapsulamiento.

Complejidad Ciclomática / Condicional Complejo: Líneas 11 a 22. Un if-else encadenado con múltiples comprobaciones booleanas difíciles de leer a simple vista.

b) Refactoring a aplicar:
Para simplificar el condicional, la técnica principal es Decompose Conditional (Descomponer Condicional) combinada con Move Method (Mover Método) basado en el principio del Experto en Información.
La lógica de saber si la temperatura es crítica pertenece a la Habitacion, ya que ella tiene los datos.

Código resultante (simplificado):

Java
// En la clase Habitacion (el Experto)
public boolean estaFueraDeRango() {
    return this.getTemperaturaActual() < this.getTemperaturaMinima() || 
           this.getTemperaturaActual() > this.getTemperaturaMaxima();
}

public boolean estaEnAlertaMinima() {
    return (this.getTemperaturaActual() - this.getTemperaturaMinima() == 0) || 
           (this.getTemperaturaActual() - this.getTemperaturaMinima() == 1);
}

// ... (y lo mismo para AlertaMaxima) ...

// En el SistemaControlTemperatura (el condicional simplificado):
public void chequearValoresTemperatura() {
    for (Habitacion h : i.habitaciones()) {
        if (h.estaFueraDeRango()) {
            this.emitirSonidoAlerta();
            this.notificarTempFueraDeRango(h, h.getTemperaturaActual());
        } else if (h.estaEnAlertaMinima()) {
            this.notificarTempEnMin(h, h.getTemperaturaActual());
            this.setTimer(5);
        } else if (h.estaEnAlertaMaxima()) {
            this.notificarTempAlMax(h, h.getTemperaturaActual());
            this.setTimer(2);
        }
    }
}
(Nota: Quedaría aún mejor si el inmueble maneja su propia iteración para no exponer la lista, pero respondiendo a la consigna estricta de "simplificar el condicional", esta es la respuesta esperada).

# Ejercicio 3: Frameworks

A - Falso. Las librerías de clases son colecciones de funciones, los frameworks son las aplicaciones "semicompletas".

B - Falso. En una librería de clases, tu código controla a la librería. En un framework ocurre la Inversión de Control.

C - Correcto. Los hotspots son los puntos de enganche donde inyectás tu código.

D - Falso. Las clases en un framework están fuertemente acopladas entre sí para conformar la arquitectura base.

E - Correcto. Colaboran estrechamente para resolver el dominio principal del problema.
(Opciones a marcar: C y E).

# Ejercicio 4: Test Doubles

A - Falso. No reducen casos de prueba, aíslan el Sistema Bajo Prueba (SUT).

B - Correcto. Si tu componente depende de una API que el equipo de backend todavía no terminó, armás un Mock/Stub para poder avanzar con tus tests.

C - Correcto. Permite aislar el objeto asegurándote de que si el test falla, es por culpa de ese objeto y no de sus colaboradores externos (ej: que la base de datos esté caída).
(Opciones a marcar: B y C).