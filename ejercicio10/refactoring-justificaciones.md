Refactoring, code smells:

* Iteración 1: La generación de números
(i) Mal olor: Switch Statements / Type Code (Sentencias Switch / Código de tipo). En GestorNumerosDisponibles.obtenerNumeroLibre(), hay un switch basado en un String (tipoGenerador) para decidir cómo obtener una línea.

(ii) Refactoring: Replace Conditional Logic with Strategy (Reemplazar lógica condicional con Estrategia).

(iii) Aplicación: Creamos la interfaz GeneradorStrategy y sus clases concretas

* Iteración 2: El cálculo del monto
(i) Mal olor: Feature Envy (Envidia de características). En Empresa.calcularMontoTotalLlamadas(), la clase Empresa le pide los datos a Llamada y hace cuentas que le corresponden a la llamada, y le pide el tipo a Cliente para aplicar descuentos.

(ii) Refactoring: Move Method (Mover Método).

(iii) Aplicación:
Movemos el cálculo base a Llamada y la aplicación del descuento a Cliente

* Iteración 3: Los tipos de Llamada
(i) Mal olor: Type Code / Switch Statements. Ahora que movimos calcularCostoBase() a Llamada, nos queda un if/else preguntando si es "nacional" o "internacional".

(ii) Refactoring: Replace Type Code with Subclasses (Reemplazar código de tipo por subclases) y luego Replace Conditional with Polymorphism (Reemplazar condicional con polimorfismo).

(iii) Aplicación:
Hacemos Llamada abstracta y creamos las subclases

* Iteración 4: Los tipos de Cliente
(i) Mal olor: Type Code / Switch Statements. En Cliente.aplicarDescuento() tenemos un if/else preguntando si es "fisica" o "juridica", además de tener atributos dni y cuit mezclados en la misma clase que nunca se usan a la vez.

(ii) Refactoring: Replace Type Code with Subclasses y Replace Conditional with Polymorphism.

(iii) Aplicación:
Hacemos Cliente abstracta, limpiamos variables inútiles 

* Iteración 5: Encapsulamiento
(i) Mal olor: Public Field / Inappropriate Intimacy (Campo público / Intimidad inapropiada). En Cliente, la lista public List<Llamada> llamadas permite que cualquiera la modifique sin control.

(ii) Refactoring: Encapsulate Collection (Encapsular Colección).

(iii) Aplicación hacer collección privada y metodo para acceder a ella.

(iv) Simplificar agregarNumeroTelefono():

Este método tiene un mal olor clásico llamado Complicated Boolean Expression (o lógica innecesariamente compleja).
Fijate lo que hace actualmente: pregunta si el número está, si no está lo agrega, setea un booleano y lo retorna. En Java, la interfaz Set (que es lo que usa SortedSet) al hacer .add() devuelve automáticamente true si lo pudo agregar y false si ya existía. Reducir a simplemente:

public boolean agregarNumeroTelefono(String str) {
    return guia.getLineas().add(str);
}


----------------
La regla de oro del refactoring dice que "no se debe alterar el comportamiento observable del sistema". Esto significa que si antes la aplicación daba "A", después del refactoring tiene que seguir dando "A". Los tests existen para garantizar justamente eso: que no rompiste la lógica de negocio.

Sin embargo, cuando aplicás refactorings estructurales que modifican la interfaz pública de tus clases (como Change Function Declaration o Replace Type Code with Strategy), estás obligado a actualizar los tests.

Lo que NO podés tocar en el test son las aserciones (los assertEquals). Lo que SÍ debés tocar es la forma en que instanciás o configurás los objetos para adaptarlos a la nueva arquitectura.