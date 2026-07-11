# Ejercicio 1: Frameworks y Patrón Builder
1. YouTourFramework es:

Respuesta: Un Framework de caja negra.

- Justificación: El framework se reutiliza mediante la composición de objetos y la inyección de dependencias. El cliente no necesita heredar de TourCreator (el framework ya provee LowCostCreator y HighEndCreator), sino que inyecta su propia implementación de ResourceRepository a través del método setResourceRepository(). Al ensamblar componentes en lugar de subclasificarlos, es Caja Negra.

2. En YouTour la jerarquía TourCreator:

Respuesta: Es un Hotspot para extender el framework.

- Justificación: Un Hotspot es la zona de flexibilidad del framework. La jerarquía TourCreator es el punto de extensión natural donde el framework permite introducir variabilidad (cómo se construye específicamente un tour) sin modificar el esqueleto principal (Frozenspot) definido en el Director.

3. Análisis del Patrón Builder:

a. ¿La estructura es correcta? Sí. Se identifican claramente el Director (YouTour), la abstracción del Builder (TourCreator), los Builders concretos y el Producto (Tour).

b. ¿Jerarquía de Builders es correcta? Sí. En el diseño puro del patrón GoF, la clase abstracta define el contrato (las firmas de los métodos). Es correcto que los métodos de construcción sean abstractos, ya que la implementación interna y la representación del producto pueden variar radicalmente entre creadores concretos. No se asume código duplicado solo por ver firmas abstractas en el UML.

c. Método YouTour.createTour() es correcto: Sí. El Director cumple su única responsabilidad: definir el orden de ejecución de los pasos. Lo hace delegando en la interfaz TourCreator, respetando el polimorfismo y sin acoplarse a las implementaciones concretas.

4. Requerimiento: Tours sin transporte

Respuesta: Crear una nueva clase que funcione como Director.

Justificación: El patrón Builder establece que el Director es el dueño del orden y los pasos de construcción. Si el algoritmo de ensamblaje cambia (se omite el transporte), se debe proveer un nuevo Director. Modificar el actual violaría el principio Open/Closed.

- UML Resultante (Descripción para la hoja): 
* Dibujar la clase original YouTour con su método +createTour(req: JSON): String.
* Dibujar una nueva clase YouTourSinTransporte con su método +createTour(req: JSON): String.
* Trazar una flecha de asociación dirigida (línea continua, punta abierta >) desde YouTour hacia TourCreator.
* Trazar otra flecha de asociación dirigida desde YouTourSinTransporte hacia TourCreator.

# Ejercicio 2: Refactoring y Malos Olores
1. El refactoring "Extract Method" puede aplicarse para eliminar el mal olor "Código duplicado".

Verdadero: Es la solución directa. Se extrae el bloque de código repetido a un método con un nombre descriptivo y se invoca desde los múltiples lugares originales.

2. El refactoring "Replace Temp With Query" puede aplicarse para eliminar el mal olor "Envidia de atributo".

Falso: "Envidia de atributo" se soluciona con "Move Method" o "Extract Method" (moviendo el comportamiento hacia donde están los datos). "Replace Temp With Query" sirve para eliminar variables temporales locales complejas o redundantes.

3. El refactoring "Move Field" puede aplicarse para eliminar el mal olor "Inappropriate Intimacy".

Verdadero: Si una clase husmea demasiado en las variables internas de otra, mover ese campo a la clase que más lo utiliza rompe esa intimidad inapropiada y reduce el acoplamiento.

4. Solo debemos invertir esfuerzo en refactoring cuando el código se vuelve ilegible.

Falso: El refactoring es una práctica proactiva y continua (parte integral de metodologías como TDD y XP), no una medida reactiva de emergencia. Se aplica para mejorar el diseño general, la mantenibilidad y la extensibilidad, no solo la legibilidad.

# Ejercicio 3: Refactoring y Tests
1. Un refactoring bien hecho, nunca rompe los tests de unidad.

Falso: Si los tests fueron diseñados acoplándose a la estructura interna o a métodos privados (tests frágiles), un refactoring estructural perfecto (como aplicar un patrón de diseño) romperá el test de compilación o de ejecución.

2. Si hago refactoring con mucho cuidado, no es necesario que programe ni ejecute tests.

Falso: El cuidado humano es falible. Los tests son la única garantía objetiva para asegurar que no se introdujeron bugs de regresión.

3. Durante el refactoring, los tests de unidad son necesarios para asegurar que no se altera el comportamiento.

Verdadero: Es el pilar fundamental del refactoring: cambiar la estructura interna manteniendo el comportamiento observable intacto y validado por los tests.

4. Si aplico refactorings con una herramienta automática, los tests se actualizan de manera automática.

Falso: El IDE puede ayudar con refactorings sintácticos simples (como renombrar), pero no tiene la capacidad de reescribir la lógica de validación de un test ante cambios estructurales complejos.

# Ejercicio 4: Test Doubles
- A. Es necesario disminuir el número de casos de prueba.

Falso: Los dobles de prueba aíslan el componente testeado, pero no reducen la cantidad de escenarios a validar.

- B. Es necesario probar la funcionalidad de un objeto independientemente del comportamiento de otro objeto, cuyos mensajes son invocados por la funcionalidad siendo testeada.

Verdadero: Describe el uso de Stubs o Fakes, permitiendo probar la lógica de negocio aislando el sistema de dependencias lentas, inestables o no implementadas (como una API externa).

- C. Son indispensables para aumentar la cobertura del testing.

Falso: La cobertura se puede lograr con tests de integración. Los dobles son indispensables para el aislamiento y la velocidad de los tests unitarios, no estrictamente para el porcentaje de cobertura.

- D. Es necesario verificar salidas indirectas del sistema.

Verdadero: Esta es la definición exacta de cuándo usar un Mock Object. Si tu método bajo prueba no devuelve un valor (salida directa), sino que su trabajo es llamar a un método de otra clase (ej. emailService.enviar()), usás un Mock para verificar que esa "salida indirecta" (la llamada al método) realmente ocurrió con los parámetros correctos.

- E. Hay objetos de los que depende el objeto a testear que aún no fueron creados.

Verdadero: Es el escenario clásico para un Stub o un Fake. Si estás desarrollando tu módulo pero el equipo de base de datos todavía no terminó su parte, creás un "doble" de la base de datos que devuelva datos fijos (harcodeados) para poder compilar y probar tu código sin tener que esperar a que el otro objeto exista en la realidad.

# Ejercicio 5: TDD y Metodologías Ágiles
1. Los tests de unidad se programan una vez que el programa no sufrirá más cambios.

Falso: Corresponde al modelo obsoleto en cascada. En las metodologías ágiles, los tests evolucionan constantemente a la par del código.

2. Test Driven Development es una metodología que evita que se genere Deuda Técnica.

Falso: TDD promueve un buen diseño y alta cobertura, pero no inmuniza contra la Deuda Técnica Inadvertida (por falta de comprensión del dominio) ni impide que el equipo adquiera Deuda Prudente/Deliberada para salir rápido al mercado.

3. En TDD, cada ciclo de desarrollo se conoce como "Red - Green - Refactor".

Verdadero: Es el flujo de trabajo esencial: Test que falla (Red), código mínimo para pasar (Green), limpiar el diseño (Refactor).

4. Puede ocurrir que un refactoring rompa los tests de unidad aunque el refactoring sea correcto.

Verdadero: Reafirma el concepto del punto 3.1. Un test excesivamente acoplado a la implementación interna se romperá ante una mejora arquitectónica correcta.

5. En la metodología Extreme Programming (XP) se considera que la arquitectura surge desde el código.

Verdadero: Es el concepto de "Diseño Emergente". La arquitectura se construye y refina iterativamente a medida que avanza el proyecto, rechazando el diseño total inicial por adelantado (BDUF).