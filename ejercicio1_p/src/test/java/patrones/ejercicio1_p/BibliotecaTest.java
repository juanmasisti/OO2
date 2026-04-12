package patrones.ejercicio1_p;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BibliotecaTest {
	private Biblioteca biblioteca;
	private Socio socio1;
	private Socio socio2;
	
	
	@BeforeEach 
	public void setUp() {
		biblioteca = new Biblioteca();
		biblioteca.setExporter(new JSONSimpleAdapter());
		
		socio1 = new Socio("Arya Stark", "needle@stark.com", "5234-5");
		socio2 = new Socio("Tyron Lannister", "tyrn@thelannisters.com", "2345-2");
		
		biblioteca.agregarSocio(socio1);
		biblioteca.agregarSocio(socio2);
		
	}

	
	@Test
	public void testExportarSocios() {
		String resultado = biblioteca.exportarSocios();
		
		assertTrue(resultado.contains("Arya Stark"));
		assertTrue(resultado.contains("5234-5"));
		assertTrue(resultado.contains("Tyron Lannister"));
		
		// Verificamos que tenga estructura básica de json
		assertTrue(resultado.startsWith("["));
		assertTrue(resultado.endsWith("]"));
	}
}
