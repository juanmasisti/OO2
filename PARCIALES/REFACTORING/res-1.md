* El método comprar() de la clase Cliente tiene varios bad smells:
1) El código de tipo
(i) Mal olor: Type Code (o Switch Statements / Condicionales basados en tipo). El código evalúa el String "tipo" para decidir el cálculo. Además, este tipo muta a lo largo del tiempo.
(ii) Refactoring: Replace Type Code with State/Strategy.
(iii) Aplicación:
Creamos la interfaz TipoCliente (o clase abstracta) con las implementaciones concretas Basico, Premium y Advance. Mudamos el cálculo de temp1 (que en realidad es un porcentaje de envío) hacia allí mediante Move Method.

- Código cambiado:
// Interfaz / State
public interface TipoCliente {
    double getDescuentoEnvio();
}

public class Basico implements TipoCliente {
    public double getDescuentoEnvio() { return 0.1; }
}

public class Premium implements TipoCliente {
    public double getDescuentoEnvio() { return 0.05; }
}

public class Advance implements TipoCliente {
    public double getDescuentoEnvio() { return 0; }
}

public class Cliente {
    private String nombre;
    private TipoCliente tipo; // Reemplazo del String por la interfaz
    private List<Compra> compras;

    public Cliente(String unNombre) {
        this.nombre = unNombre;
        this.tipo = new Basico();
        this.compras = new ArrayList<>();
    }

    public Compra comprar(List<Producto> productos) {
        // La cadena de ifs desaparece por polimorfismo
        double temp1 = this.tipo.getDescuentoEnvio(); 
        
        double subtotal = productos.stream().mapToDouble(p -> p.getPrecio()).sum();
        double costoEnvio = subtotal * temp1;
        Compra n = new Compra(productos, subtotal, costoEnvio);
        this.compras.add(n);

        // Los ifs de actualización todavía existen, pero ahora instancian estados
        if (this.montoAcumuladoEnCompras() > 10000) {
            this.tipo = new Advance();
        } else if (this.montoAcumuladoEnCompras() > 5000) {
            this.tipo = new Premium();
        }
        return n;
    }
}

2) La variable mal nombrada
(i) Mal olor: Uncommunicative Name (Nombre poco comunicativo). La variable temp1 no comunica que en realidad representa el porcentaje de descuento del costo de envío.
(ii) Refactoring: Rename Variable.
(iii) Aplicación: Cambiamos temp1 por porcentajeEnvio.

- Código cambiado:
public Compra comprar(List<Producto> productos) {
    // Rename Variable
     double porcentajeEnvio = this.tipo.getDescuentoEnvio(); 
        
    double subtotal = productos.stream().mapToDouble(p -> p.getPrecio()).sum();
    double costoEnvio = subtotal * porcentajeEnvio;
    Compra n = new Compra(productos, subtotal, costoEnvio);
    this.compras.add(n);

    if (this.montoAcumuladoEnCompras() > 10000) {
        this.tipo = new Advance();
    } else if (this.montoAcumuladoEnCompras() > 5000) {
        this.tipo = new Premium();
    }
    return n;
}

3) Long Method (Método largo comprar()).
(i) Mal olor: Long Method. El método de comprar no solo registra la compra, sino que se hace cargo de evaluar si el cliente debe subir de categoría, mezclando responsabilidades.
(ii) Refactoring: Extract Method.
(iii) Aplicación: Extraemos la lógica de actualización de estado a un método privado actualizarCategoria().

- Código cambiado:
public Compra comprar(List<Producto> productos) {
    double porcentajeEnvio = this.tipo.getDescuentoEnvio(); 
    double subtotal = productos.stream().mapToDouble(p -> p.getPrecio()).sum();
    double costoEnvio = subtotal * porcentajeEnvio;
        
    Compra n = new Compra(productos, subtotal, costoEnvio);
    this.compras.add(n);

    this.actualizarCategoria(); // Llamada al método extraído
        
    return n;
}

// Nuevo método extraído
private void actualizarCategoria() {
    if (this.montoAcumuladoEnCompras() > 10000) {
        this.tipo = new Advance();
    } else if (this.montoAcumuladoEnCompras() > 5000) {
        this.tipo = new Premium();
    }
}

Paso 4: Inline Temp y Uncommunicative Name (Variable temporal y nombre poco comunicativo).
(i) Mal olor: Inline Temp (o Temporary Field). La variable porcentajeEnvio se usa una sola vez y no aporta demasiado valor frente a la expresión directa. Por otro lado la variable n no es un nombre que comunique el proposito de la variable, que es representar la compra realizada.
(ii) Refactoring: Inline Temp (Incorporar variable temporal) y Rename Variable.
(iii) Aplicación: Eliminamos la variable porcentajeEnvio, usando directamente la expresión en su lugar. Renombramos la variable n por nuevaCompra.

- Código cambiado:
public Compra comprar(List<Producto> productos) {
    double subtotal = productos.stream().mapToDouble(p -> p.getPrecio()).sum();
    double costoEnvio = subtotal * this.tipo.getDescuentoEnvio();

    Compra nuevaCompra = new Compra(productos, subtotal, costoEnvio);
    this.compras.add(nuevaCompra);

    this.actualizarCategoria();
    return nuevaCompra;
}