1. Justificación de los Patrones:
Patrones utilizados: Adapter y Decorator.

- Justificación de Adapter:
El objetivo de Adapter es convertir la interfaz de una clase en otra interfaz que los clientes esperan. Se utiliza porque tenemos la clase de terceros HomeWeatherStation que no podemos modificar, y necesitamos adaptar su interfaz a nuestro sistema, extendiéndola para agregar las nuevas responsabilidades solicitadas (el cálculo de grados Celsius y el promedio histórico).

- Justificación de Decorator:
El objetivo de Decorator es añadir responsabilidades dinámicamente a un objeto en tiempo de ejecución. Se utiliza para permitir al usuario configurar qué datos mostrar y en qué orden al llamar a displayData(), proporcionando una alternativa flexible a la herencia y evitando una explosión combinatoria de subclases para cada formato de pantalla posible.

2. Diseño UML (Roles)

HomeWeatherStation: Es el <<Adaptee>> (el adaptado, la clase de terceros).

ComponenteClima (Interfaz): Es el <<Target>> del Adapter y, al mismo tiempo, el <<Component>> del Decorator. Define todos los getters y el método displayData().

WeatherAdapter: Es el <<Adapter>> y el <<ConcreteComponent>>. Envuelve a la estación de terceros y resuelve los cálculos nuevos (Celsius y promedio). Su displayData() devuelve un string vacío (es el punto de partida).

DisplayDecorator: Es el <<Decorator>> abstracto.

PresionDecorator, RadiacionDecorator, etc.: Son los <<ConcreteDecorator>>.

3. Implementación en Java

// 1. LA CLASE DE TERCEROS (Adaptee - No se toca)
class HomeWeatherStation {
    public double getTemperaturaFahrenheit() { return 82.4; } // Ejemplos hardcodeados
    public double getPresion() { return 1008.0; }
    public double getRadiacionSolar() { return 500.0; }
    public List<Double> getTemperaturasFahrenheit() { return List.of(75.0, 78.0, 82.4); }
}

// 2. NUESTRA INTERFAZ (Target + Component)
public interface ComponenteClima {
    double getTemperaturaFahrenheit();
    double getPresion();
    double getRadiacionSolar();
    List<Double> getTemperaturasFahrenheit();
    double getTemperaturaCelsius();
    double getPromedioFahrenheit();
    
    String displayData(); // El método clave para imprimir
}

// 3. EL ADAPTER (Adapter + ConcreteComponent)
public class WeatherAdapter implements ComponenteClima {
    private HomeWeatherStation station;

    public WeatherAdapter(HomeWeatherStation station) {
        this.station = station;
    }

    // Delegamos los métodos existentes
    @Override
    public double getTemperaturaFahrenheit() { return station.getTemperaturaFahrenheit(); }
    @Override
    public double getPresion() { return station.getPresion(); }
    @Override
    public double getRadiacionSolar() { return station.getRadiacionSolar(); }
    @Override
    public List<Double> getTemperaturasFahrenheit() { return station.getTemperaturasFahrenheit(); }

    // Implementamos los NUEVOS requerimientos
    @Override
    public double getTemperaturaCelsius() {
        return (this.getTemperaturaFahrenheit() - 32) / 1.8;
    }

    @Override
    public double getPromedioFahrenheit() {
        List<Double> historial = this.getTemperaturasFahrenheit();
        if (historial.isEmpty()) return 0;
        double suma = historial.stream().mapToDouble(Double::doubleValue).sum();
        return suma / historial.size();
    }

    // Base del Decorator (Devuelve vacío para empezar a concatenar)
    @Override
    public String displayData() {
        return ""; 
    }
}

// 4. EL DECORATOR ABSTRACTO
public abstract class DisplayDecorator implements ComponenteClima {
    protected ComponenteClima wrapper;

    public DisplayDecorator(ComponenteClima wrapper) {
        this.wrapper = wrapper;
    }

    // El decorator delega por defecto todo a su wrapper
    @Override
    public double getTemperaturaFahrenheit() { return wrapper.getTemperaturaFahrenheit(); }
    @Override
    public double getPresion() { return wrapper.getPresion(); }
    @Override
    public double getRadiacionSolar() { return wrapper.getRadiacionSolar(); }
    @Override
    public List<Double> getTemperaturasFahrenheit() { return wrapper.getTemperaturasFahrenheit(); }
    @Override
    public double getTemperaturaCelsius() { return wrapper.getTemperaturaCelsius(); }
    @Override
    public double getPromedioFahrenheit() { return wrapper.getPromedioFahrenheit(); }

    @Override
    public String displayData() {
        return wrapper.displayData();
    }
}

// 5. LOS DECORADORES CONCRETOS
public class PresionDecorator extends DisplayDecorator {
    public PresionDecorator(ComponenteClima wrapper) { super(wrapper); }

    @Override
    public String displayData() {
        // Llama al anterior, y le suma lo suyo
        String base = super.displayData();
        String separador = base.isEmpty() ? "" : " "; // Para que los espacios queden prolijos
        // Formateo simple casteando a int para que quede igual al ejemplo del parcial "1008"
        return base + separador + "Presión atmosférica: " + (int)this.getPresion();
    }
}

public class RadiacionDecorator extends DisplayDecorator {
    public RadiacionDecorator(ComponenteClima wrapper) { super(wrapper); }

    @Override
    public String displayData() {
        String base = super.displayData();
        String separador = base.isEmpty() ? "" : " ";
        return base + separador + "Radiación solar: " + (int)this.getRadiacionSolar();
    }
}
// (Acá irían los decoradores para Temperatura y Promedio de la misma forma)


4. Test Unitario (Punto 3 del Parcial)
Te piden validar la configuración del Ejemplo 2, que debe devolver exactamente: "Presión atmosférica: 1008 Radiación solar: 500".

La magia del Decorator se ve en el test: armamos un objeto como si fueran "muñecas mamushka". El orden en el que instanciamos los decoradores define el orden en el que se imprimen los datos.

Java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EstacionMeteorologicaTest {
    
    private HomeWeatherStation estacionTerceros;
    private ComponenteClima displayEjemplo2;

    @BeforeEach
    void setUp() {
        // 1. Instanciamos la API de terceros (asumiendo que devuelve 1008 y 500)
        estacionTerceros = new HomeWeatherStation();
        
        // 2. La metemos en nuestro Adapter
        ComponenteClima adapterBase = new WeatherAdapter(estacionTerceros);
        
        // 3. Armamos la configuración del Ejemplo 2 con los Decorators.
        // Ojo al orden: queremos que Presión quede a la izquierda, 
        // así que envolvemos Presión ADENTRO de Radiación.
        displayEjemplo2 = new RadiacionDecorator(new PresionDecorator(adapterBase));
    }

    @Test
    void testConfiguracionEjemplo2() {
        String resultadoEsperado = "Presión atmosférica: 1008 Radiación solar: 500";
        assertEquals(resultadoEsperado, displayEjemplo2.displayData());
    }
}