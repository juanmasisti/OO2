Arquetipos de Orientacion a Objetos
* Ejercicio 6:

* * 6.1 Empleados
Este código muestra tres clases: EmpleadoTemporario, EmpleadoPlanta y EmpleadoPasante.

- Iteración 1:

(i) Mal olor: Código Duplicado (Duplicated Code) y Romper Encapsulamiento. Los atributos nombre, apellido y sueldoBasico están repetidos en las tres clases y declarados como public. Además, el método sueldo() tiene firmas idénticas.
(ii) Refactoring: Extract Superclass (Extraer Superclase) para agrupar los atributos comunes y Encapsulate Field para hacerlos privados/protegidos.
(iii) Aplicación: Creamos la superclase Empleado.

- Iteración 2:
(i) Mal olor: Código Duplicado. El método sueldo() hace un cálculo similar en todos lados pero difiere en partes específicas.
(ii) Refactoring: Pull Up Method (para subir sueldo()) combinado con el patrón Template Method (Form Template Method, p.345 en Fowler). Separamos la parte constante de la variable.

* * 6.2 Juego 
El código muestra a la clase Juego modificando directamente la variable puntuacion de la clase Jugador.

(i) Mal olor: Feature Envy (Envidia de Atributo) y Romper encapsulamiento. La clase Juego usa los datos internos de Jugador para hacer los cálculos, y para que esto sea posible, puntuacion es public.
(ii) Refactoring: Move Method (Mover Método) y luego Encapsulate Field.

* * 6.3 Publicaciones 
Este es un caso clásico de un método monstruosamente largo y confuso.


(i) Mal olor: Long Method (Método Largo). El método ultimosPosts hace de todo: filtra posts de otros usuarios, los ordena de forma manual y compleja (con un algoritmo de burbujeo arcaico), y los pagina.
(ii) Refactoring: Extract Method y Substitute Algorithm.
(iii) Aplicación: Rompemos el método gigante en métodos más chicos con nombres claros y usamos Streams para sustituir el algoritmo manual.

public List<Post> ultimosPosts(Usuario user, int cantidad) {
    return this.posts.stream()
            .filter(post -> !post.getUsuario().equals(user)) // Filtra otros usuarios
            .sorted(Comparator.comparing(Post::getFecha).reversed()) // Ordena por fecha descendente
            .limit(cantidad) // Toma la cantidad deseada
            .collect(Collectors.toList());
}

* * 6.4 Carrito de compras 
El método total() en Carrito le pide al item su producto, luego al producto su precio, y luego al item su cantidad.


(i) Mal olor: Message Chains (Cadenas de Mensajes) y Feature Envy. El Carrito navega a través de la estructura interna del ItemCarrito para acceder a los datos del Producto (item.getProducto().getPrecio()). Esto genera alto acoplamiento.
(ii) Refactoring: Hide Delegate (Ocultar Delegado) en ItemCarrito o Move Method.
(iii) Aplicación: La responsabilidad de calcular el subtotal es del Item.

// Código final refactorizado:
public class ItemCarrito {
    private Producto producto;
    private int cantidad;

    // Nuevo método: El Item se encarga de calcular su propio subtotal
    public double getSubtotal() {
        return this.producto.getPrecio() * this.cantidad;
    }
}

public class Carrito {
    private List<ItemCarrito> items;

    public double total() {
        return this.items.stream()
                         .mapToDouble(ItemCarrito::getSubtotal) // Usa el nuevo método
                         .sum();
    }
}

* * 6.5 Envío de pedidos 
El cliente arma su propia dirección concatenando strings accediendo a atributos internos de Direccion.


(i) Mal olor: Feature Envy (en Cliente respecto a Direccion).
(ii) Refactoring: Move Method (getDireccionFormateada debería estar en la clase Direccion).
(iii) Aplicación:

// Código final refactorizado:
public class Direccion {
    private String localidad;
    private String calle;
    private String numero;
    private String departamento;

    // El método se movió acá, donde están los datos reales
    public String formatear() {
        return this.localidad + ", " + this.calle + ", " + this.numero + ", " + this.departamento;
    }
}

public class Cliente {
    private Direccion direccion;

    public String getDireccionFormateada() {
        return this.direccion.formatear(); // Delegación pura
    }
}

* * 6.6 Películas 
Acá tenemos el clásico problema de usar condicionales basándose en tipos (strings en este caso).

(i) Mal olor: Switch Statements (Sentencias Switch / if-else encadenados) y el uso de "Magic Strings" ("Basico", "Familia").
(ii) Refactoring: Replace Conditional with Polymorphism (Reemplazar Condicional con Polimorfismo) e Introduce State/Strategy(patron de diseño).
(iii) Aplicación: Creamos una jerarquía para la Suscripción, sacando ese String que no aporta valor.

// Código final refactorizado:
public abstract class Suscripcion {
    public abstract double calcularCosto(Pelicula pelicula);
}

public class SuscripcionBasico extends Suscripcion {
    @Override
    public double calcularCosto(Pelicula pelicula) {
        return pelicula.getCosto() + pelicula.calcularCargoExtraPorEstreno();
    }
}

public class SuscripcionFamilia extends Suscripcion {
    @Override
    public double calcularCosto(Pelicula pelicula) {
        return (pelicula.getCosto() + pelicula.calcularCargoExtraPorEstreno()) * 0.90;
    }
}

// ... etc para Plus y Premium

public class Usuario {
    private Suscripcion suscripcion; // Ya no es un String

    public void setSuscripcion(Suscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }

    public double calcularCostoPelicula(Pelicula pelicula) {
        // Adiós a los ifs. Polimorfismo en acción.
        return this.suscripcion.calcularCosto(pelicula);
    }
}
