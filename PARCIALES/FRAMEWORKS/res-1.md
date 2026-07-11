* 1. Dado que para utilizar este framework usted tiene que implementar una subclase de Rule, la ejecución del código de esta subclase, ¿se realiza mediante inversión de control? Justifique su respuesta de forma concisa.

Sí, absolutamente. La ejecución se realiza mediante Inversión de Control (IoC), también conocida como el "Principio de Hollywood" (No nos llames, nosotros te llamaremos).
Como desarrolladores, nosotros escribimos la lógica de negocio específica dentro de las subclases de Rule (implementando shouldProcess() y process()), pero nuestro código nunca decide cuándo ejecutarse. Es el motor del framework (la clase RuleEngine) quien tiene el control del flujo principal a través de su propio método run(). El framework itera sobre la colección de reglas y es él quien llama a nuestro código en el momento que considera adecuado.

* 2. ¿Cuáles son los hook methods?

Los hook methods (métodos gancho) son:
- shouldProcess()
- process()

Ambos pertenecen a la clase abstracta Rule. Son los "puntos calientes" (Hot Spots) del framework. El framework los declara como abstractos para obligar a que nosotros (los usuarios del framework) proveamos la implementación concreta, inyectando así nuestro comportamiento personalizado dentro de la arquitectura base.

* 3. Describa, de forma concisa, el frozen spot del extracto del framework presentado.

El frozen spot (código inmutable) está compuesto por la estructura que define el esqueleto del comportamiento general y que el usuario no puede (ni debe) modificar. En este extracto, se identifica claramente en dos lugares:

- El método run() de la clase abstracta Rule: Actúa como un Template Method. Congela el algoritmo base estableciendo una regla inquebrantable: el método process() se ejecutará pura y exclusivamente si la evaluación de shouldProcess() retorna verdadero.
- El método run() de la clase RuleEngine: Congela el flujo general de la aplicación, definiendo que el motor siempre va a iterar secuencialmente sobre todas las reglas inyectadas y ejecutará el método run() de cada una de ellas.