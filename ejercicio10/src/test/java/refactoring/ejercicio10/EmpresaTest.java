package refactoring.ejercicio10;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmpresaTest {
	Empresa sistema;

	@BeforeEach
	public void setUp() {
		this.sistema = new Empresa();
		this.sistema.agregarNumeroTelefono("2214444554");
		this.sistema.agregarNumeroTelefono("2214444555");
		this.sistema.agregarNumeroTelefono("2214444556");
		this.sistema.agregarNumeroTelefono("2214444557");
		this.sistema.agregarNumeroTelefono("2214444558");
		this.sistema.agregarNumeroTelefono("2214444559");
	}

	@Test
	void testcalcularMontoTotalLlamadas() {
	    // Corregimos el orden de los parámetros para que coincida con tus constructores
	    PersonaFisica emisorPersonaFisica = new PersonaFisica("Brendan Eich", "11555666");
	    emisorPersonaFisica.setNumeroTelefono(sistema.obtenerNumeroLibre());
	    sistema.registrarUsuario(emisorPersonaFisica);

	    PersonaJuridica emisorPersonaJuridica = new PersonaJuridica("Nvidia Corp", "17555222");
	    emisorPersonaJuridica.setNumeroTelefono(sistema.obtenerNumeroLibre());
	    sistema.registrarUsuario(emisorPersonaJuridica);
	    
	    PersonaFisica remitentePersonaFisica = new PersonaFisica("Doug Lea", "00000001");
	    remitentePersonaFisica.setNumeroTelefono(sistema.obtenerNumeroLibre());
	    sistema.registrarUsuario(remitentePersonaFisica);
	    
	    PersonaJuridica remitentePersonaJuridica = new PersonaJuridica("Sun Microsystems", "25765432");
	    remitentePersonaJuridica.setNumeroTelefono(sistema.obtenerNumeroLibre());
	    sistema.registrarUsuario(remitentePersonaJuridica);

	    // Mismas llamadas que el original, pero instanciando las clases correspondientes
	    this.sistema.registrarLlamada(emisorPersonaJuridica, 
	        new LlamadaNacional(emisorPersonaJuridica.getNumeroTelefono(), remitentePersonaFisica.getNumeroTelefono(), 10));

	    this.sistema.registrarLlamada(emisorPersonaJuridica, 
	        new LlamadaInternacional(emisorPersonaJuridica.getNumeroTelefono(), remitentePersonaFisica.getNumeroTelefono(), 8));
	    
	    this.sistema.registrarLlamada(emisorPersonaJuridica, 
	        new LlamadaNacional(emisorPersonaJuridica.getNumeroTelefono(), remitentePersonaJuridica.getNumeroTelefono(), 5));
	    
	    this.sistema.registrarLlamada(emisorPersonaJuridica, 
	        new LlamadaInternacional(emisorPersonaJuridica.getNumeroTelefono(), remitentePersonaJuridica.getNumeroTelefono(), 7));
	    
	    this.sistema.registrarLlamada(emisorPersonaFisica, 
	        new LlamadaNacional(emisorPersonaFisica.getNumeroTelefono(), remitentePersonaFisica.getNumeroTelefono(), 15));
	    
	    this.sistema.registrarLlamada(emisorPersonaFisica, 
	        new LlamadaInternacional(emisorPersonaFisica.getNumeroTelefono(), remitentePersonaFisica.getNumeroTelefono(), 45));
	    
	    this.sistema.registrarLlamada(emisorPersonaFisica, 
	        new LlamadaNacional(emisorPersonaFisica.getNumeroTelefono(), remitentePersonaJuridica.getNumeroTelefono(), 13));
	    
	    this.sistema.registrarLlamada(emisorPersonaFisica, 
	        new LlamadaInternacional(emisorPersonaFisica.getNumeroTelefono(), remitentePersonaJuridica.getNumeroTelefono(), 17));

	    assertEquals(11454.64, this.sistema.calcularMontoTotalLlamadas(emisorPersonaFisica), 0.01);
	    assertEquals(2445.40, this.sistema.calcularMontoTotalLlamadas(emisorPersonaJuridica), 0.01);
	    assertEquals(0, this.sistema.calcularMontoTotalLlamadas(remitentePersonaFisica));
	    assertEquals(0, this.sistema.calcularMontoTotalLlamadas(remitentePersonaJuridica));
	}

	@Test
	void testAgregarUsuario() {
		assertEquals(this.sistema.cantidadDeUsuarios(), 0);
		this.sistema.agregarNumeroTelefono("2214444558"); 
		// Instanciamos el cliente en lugar de usar los strings
	    PersonaFisica nuevaPersona = new PersonaFisica("Alan Turing", "2444555");
	    this.sistema.registrarUsuario(nuevaPersona);

		assertEquals(1, this.sistema.cantidadDeUsuarios());
		assertTrue(this.sistema.existeUsuario(nuevaPersona));
	}

	@Test
	void obtenerNumeroLibre() {
		// por defecto es el ultimo
		assertEquals("2214444559", this.sistema.obtenerNumeroLibre());

		this.sistema.getGestorNumeros().cambiarTipoGenerador(new GeneradorPrimero());
		assertEquals("2214444554", this.sistema.obtenerNumeroLibre());

		this.sistema.getGestorNumeros().cambiarTipoGenerador(new GeneradorRandom());
		assertNotNull(this.sistema.obtenerNumeroLibre());
	}
}
