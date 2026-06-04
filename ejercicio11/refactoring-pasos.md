* Secuencia de Refactoring para (Introduce Null Object):

(i) Mal olor: Null Check (Chequeo de Nulos). En la clase ArbolBinario, los métodos recorrerPreorden(), recorrerInorden() y recorrerPostorden() están plagados de condiciones if (this.getHijoIzquierdo() != null) e if (this.getHijoDerecho() != null).

(ii) Refactoring: Introduce Null Object (Introducir Objeto Nulo).

(iii) Aplicación (Los Pasos de la Mecánica):

- Crear el null object: Extraemos una clase abstracta Arbol (o interfaz mejor para este caso no comparten ninguna variable de estado (el ArbolVacio no tiene valor, ni hijo izquierdo, ni hijo derecho) ni tampoco comparten implementaciones de métodos (uno hace concatenación recursiva y el otro devuelve ""), usar una interfaz es la solución más limpia y desacoplada.) para generalizar el comportamiento, y creamos ArbolVacio (el Null Object) y NodoArbol (el Real Object, la clase original adaptada).

- Redefinir métodos (comportamiento alternativo): Identificamos los métodos del código cliente (recorrerPreorden, recorrerInorden, recorrerPostorden) que sufren del null check. En la clase ArbolVacio, redefinimos estos métodos para que devuelvan el comportamiento alternativo (en este caso, un String vacío "", ya que un árbol vacío no aporta nada al recorrido).

- Inicializar temprano: En el constructor de la clase real (NodoArbol), inicializamos los hijos (izquierdo y derecho) con una instancia de ArbolVacio en lugar de dejarlos en null. Bonus: Usaremos el patrón Singleton para ArbolVacio para optimizar memoria.

- Eliminar chequeos: Eliminamos los if de los recorridos en NodoArbol, delegando ciegamente a los hijos.