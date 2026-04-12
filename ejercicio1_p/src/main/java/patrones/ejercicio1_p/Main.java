package patrones.ejercicio1_p;

public class Main {
	
    public static void main(String[] args) {
        
        Biblioteca biblioteca = new Biblioteca();
        
        // 1. Le inyectamos el exportador que queremos probar.
        // Podés probar cambiarlo por new JSONSimpleAdapter() o new JacksonAdapter()
        // biblioteca.setExporter(new VoorheesExporter()); 
        
        // 2. Agregamos los socios
        biblioteca.agregarSocio(new Socio("Arya Stark", "needle@stark.com", "5234-5"));
        biblioteca.agregarSocio(new Socio("Tyron Lannister", "tyron@thelannisters.com",  "2345-2"));
        
        // 3. Imprimimos el resultado en la consola
        System.out.println(biblioteca.exportarSocios());
    }

}