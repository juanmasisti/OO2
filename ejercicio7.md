
## Para este ejercicio el problema central es que armar el árbol del Composite a mano (new ProductoCombinado().agregar(new CompraDolares()).agregar(...)) esparce la lógica de creación por todo el sistema. Además, le suma una capa de complejidad enorme: las reglas de negocio cambian según la membresía (Silver o Gold tienen distintas tasas y días de parking). La mejor solución es combinar el Composite con un patrón creacional. La propuesta ideal aquí es el Patrón Builder.

# Solucion propuesta: combinar con Patrón Builder. Se estructuría de esta forma:
1. Director: Es el "catálogo" del banco. Conoce las recetas exactas de las combinaciones. Va a tener métodos como armarProducto1(), armarProducto2(), etc. El Director sabe qué pasos hacer (ej: "agregar dólares, luego plazo fijo, luego pesos"), pero no sabe cómo se configuran internamente.

2. Builder (Abstracto): Define la interfaz para crear cada parte del producto financiero (agregarCompraDolares(), agregarPlazoFijo(), agregarBonoBajoRiesgo()). Además, tiene un método obtenerProducto() que devuelve el ProductoCombinado (el Composite) ya ensamblado.

3. Concrete Builders (SilverBuilder y GoldBuilder): ¡Acá está la clave de la solución! Tendrías un Builder por cada nivel de membresía.

4. Cuando el SilverBuilder implementa agregarPlazoFijo(), internamente hace un new PlazoFijo(35, 0.05) y lo agrega al Composite.

5. Cuando el GoldBuilder implementa el mismo método, hace un new PlazoFijo(30, 0.06).

# Ventajas y desventajas de esta solución (Trade-off):
- Ventajas:
1. Principio de Responsabilidad Única (SRP): Las clases del Composite (PlazoFijo, Bono, etc.) solo se encargan de calcular el retorno de inversión. Toda la lógica engorrosa de instanciación, tasas, parking y días de membresía se muda exclusivamente a los Builders.

2. Principio Abierto/Cerrado (OCP): * Si el banco lanza la membresía "Platinum", solo creás un PlatinumBuilder sin modificar nada del código existente.

3. Si el banco inventa el "Producto 5" (la receta nueva), solo agregás un método armarProducto5() en el Director.

4. Ocultamiento de la complejidad: Los desarrolladores consumen el Director y se olvidan de si el árbol de inversiones tiene 3 o 50 nodos anidados.

- Desventajas:
1. Rigidez ante nuevos productos base: Si el banco decide agregar una operación atómica totalmente nueva (por ejemplo, "Compra de Criptomonedas"), vas a tener que modificar la interfaz del Builder abstracto (agregando buildCompraCripto()) y, en consecuencia, actualizar todos los ConcreteBuilders existentes para implementar ese paso.

2. Aumento de clases: Sumar el Director, la interfaz Builder y los Builders concretos incrementa la cantidad de archivos y clases en el proyecto, lo que al principio puede parecer una "sobre-ingeniería" si el sistema fuera más simple (aunque en este dominio bancario está más que justificado).