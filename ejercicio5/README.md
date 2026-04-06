Arquetipos de Orientacion a Objetos
* Ejercicio 5:

Tareas:
1. Encapsulate Field (cost)
Romper el encapsulamiento dejando atributos públicos (public double cost;) es un error grave de diseño porque cualquier objeto externo puede modificar el costo sin control.

Pasos llevados a cabo:
    - Cambiar la visibilidad del atributo cost de public a private en CarRental y HotelStay.
    - Crear un método de acceso o getter (ej. public double getCost() { return this.cost; }).
    - Reemplazar todas las referencias directas al atributo por llamadas al nuevo método.

Como los tests acceden al atributo costo directamente, al cambiar su visibilidad a private, el código no compila. Modificar el test para que llame a this.alquilerAuto.getCost() es parte obligatoria del refactoring.
El test representa una prueba de estado o inicialización. Verifica que el objeto se haya construido correctamente y que los valores pasados al constructor se hayan guardado internamente como corresponde.

2. Rename Field (cost a quote) en priceFactor()
El objetivo es que el nombre de la variable refleje mejor su intención (una "cotización").

Pasos: Renombrar private double cost; a private double quote; en la declaración y dentro del método priceFactor() y el constructor.

Discusión sobre los tests:

¿Es necesario modificar los tests? Depende, si solo cambiás el nombre de la variable interna, pero dejás el método como public double getCost() { return this.quote; }, los tests no se rompen y siguen pasando. Sin embargo, por convención y claridad, el refactoring completo implica hacer un Rename Method al getter para que sea getQuote(). Si hacés esto último, sí tenés que ir al test, renombrarlo a testQuote() y actualizar la llamada.

3. 4. Precondiciones y Preparación para Pull Up Method
Te piden subir startDate() y endDate() a la superclase Product.

¿Es posible hacerlo directamente? No.

Justificación (Precondiciones): Fowler establece que para subir un método (Pull Up Method), el cuerpo del método debe ser idéntico en las subclases(el cual en este caso cumple), pero además, todos los atributos o métodos que utilice ese método deben existir o ser accesibles desde la superclase. En este caso, ambos métodos hacen return this.timePeriod.start();, pero la variable private TimePeriod timePeriod; solo existe en las subclases. Product no sabe qué es timePeriod.

Refactoring previo necesario: Pull Up Field (Subir Atributo). Primero hay que mover el atributo timePeriod a la clase abstracta Product. Para no romper el encapsulamiento, lo ideal es declararlo como protected TimePeriod timePeriod; (o privado con un getter protected) en Product para que las subclases puedan inicializarlo en sus constructores.

5. Aplicar Pull Up Method:
Una vez que el atributo timePeriod vive en la superclase (protected para que sea accesible en la subclase), copiás exactamente los métodos startDate() y endDate() a Product y los borrás de HotelStay y CarRental.

La clase Product queda así:
public abstract class Product {
    protected TimePeriod timePeriod;

    public LocalDate startDate() {
        return this.timePeriod.start();
    }

    public LocalDate endDate() {
        return this.timePeriod.end();
    }
}

Los tests siguen pasando en verde de forma transparente, ya que por herencia las instancias siguen sabiendo responder a esos mensajes.

* a consultar: usamos protected o private con método?
Justamente para solucionar esto. En la Teoría 1 (página 14), al hacer el Pull Up Field de la variable cost, se aclara explícitamente: "Debemos declarar a cost como protegida (#)".La visibilidad protected (simbolizada con un # en UML) es un nivel intermedio: significa que el atributo es privado para el mundo exterior, pero es visible y accesible directamente para las clases que heredan de ella.La alternativa (y muchas veces la mejor práctica):
Si sos un "purista" del encapsulamiento y no querés usar protected (porque a veces se considera que expone demasiado), podés dejar el atributo como private en Product, pero estás obligado a proveer métodos de acceso. Así lo menciona la Teoría 2 (página 29) al hablar del refactoring Pull Up Method: "usar 'Pull Up Field' + 'Self Encapsulate Field' y declarar los getters...".

6. Identificar Code Smells en price()
los métodos en cada subclase:
CarRental: return this.company.price() * this.company.promotionRate();
HotelStay: return this.timePeriod.duration() * this.hotel.nightPrice() * this.hotel.discountRate();

Code Smell detectado: Feature Envy (Envidia de Atributo).
Ambas clases están usando excesivamente los datos de otras clases (Company y Hotel) para hacer un cálculo. No usan sus propios datos. Están "envidiando" a las otras clases y pidiéndoles los datos crudos, lo cual rompe la cohesión.

Refactoring a aplicar: Move Method (Mover Método).
La responsabilidad de calcular el precio base debe moverse a la clase dueña de la información.

- En Company, crear un método: public double calculatePrice() { return this.price * this.promotionRate; }. Entonces CarRental.price() simplemente delega: return this.company.calculatePrice();.

- En Hotel, crear: public double calculatePrice(long duration) { return duration * this.nightPrice * this.discountRate; }. Entonces HotelStay.price() delega: return this.hotel.calculatePrice(this.timePeriod.duration());.