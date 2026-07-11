* 1. El comportamiento variable (hotspots) está implementado mediante composición. Al tratarse de un Framework de Caja Negra, el usuario no debe heredar de la clase principal (API), sino que debe proveer implementaciones concretas de la interfaz Permission e inyectarlas en la API mediante su constructor. El framework delega dinámicamente el comportamiento a estas instancias compuestas.

* 2. Sí, observo hook methods. Son los métodos allowsAccess(Request request) y throwException(Request request) de la interfaz Permission. Al declararlos en la interfaz, el framework nos provee el "gancho" para inyectar nuestra lógica de autorización.

* 3. El frozen spot (la lógica invariante) está plasmado en la clase API, específicamente a través de sus métodos que actúan como Template Methods:
- El método checkPermissions() congela el algoritmo de validación: establece que siempre se iterará sobre todos los permisos provistos y se ejecutará el hook method allowsAccess. Si este falla, obligatoriamente se llama a throwException.
- El método processRequest() congela el manejo del protocolo HTTP: establece que la única forma de retornar un HTTP 200 OK es si el bloque try finaliza sin interrupciones, y atrapa la excepción AccessDeniedException para forzar el retorno de un HTTP 403 FORBIDDEN.

* 4. Sí, se observa Inversión de Control (IoC). Nosotros programamos las clases que implementan la interfaz Permission (con nuestras reglas de negocio), pero nosotros nunca instanciamos llamadas a allowsAccess() o throwException(). El control del flujo lo tiene el framework; es la clase API, dentro de su método checkPermissions(), quien decide cuándo y en qué orden llamar a nuestros métodos concretos mientras itera la lista de permisos.

