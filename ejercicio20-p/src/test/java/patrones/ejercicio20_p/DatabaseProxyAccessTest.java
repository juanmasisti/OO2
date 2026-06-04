package patrones.ejercicio20_p;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DatabaseProxyAccessTest {
    private DatabaseProxyAccess proxy;
    private String correctPassword = "super_secreto";

    @BeforeEach
    void setUp() throws Exception {
        // Inicializamos el proxy con una contraseña
        this.proxy = new DatabaseProxyAccess(correctPassword);
    }

    @Test
    void testAccesoDenegadoSinLogin() {
        // Usamos assertThrows de JUnit 5 para verificar que explota al no estar logueado
        Exception exceptionRead = assertThrows(RuntimeException.class, () -> {
            this.proxy.getSearchResults("select * from comics where id=1");
        });
        assertEquals("Acceso denegado: Usuario no autenticado.", exceptionRead.getMessage());

        Exception exceptionWrite = assertThrows(RuntimeException.class, () -> {
            this.proxy.insertNewRow(Arrays.asList("Batman", "DC"));
        });
        assertEquals("Acceso denegado: Usuario no autenticado.", exceptionWrite.getMessage());
    }

    @Test
    void testAccesoDenegadoConLoginIncorrecto() {
        boolean loginResult = this.proxy.login("contraseña_mala");
        assertFalse(loginResult);
        
        // Verifica que sigue lanzando error
        assertThrows(RuntimeException.class, () -> {
            this.proxy.getSearchResults("select * from comics where id=1");
        });
    }

    @Test
    void testAccesoPermitidoConLoginCorrecto() {
        boolean loginResult = this.proxy.login(correctPassword);
        assertTrue(loginResult);

        // Ahora las operaciones deberían funcionar tal como funcionaba el DatabaseRealAccess original
        assertEquals(Arrays.asList("Spiderman", "Marvel"), 
            this.proxy.getSearchResults("select * from comics where id=1"));
        
        assertEquals(Collections.emptyList(), 
            this.proxy.getSearchResults("select * from comics where id=10"));
            
        assertEquals(3, this.proxy.insertNewRow(Arrays.asList("Patoruzú", "La flor")));
        assertEquals(Arrays.asList("Patoruzú", "La flor"), 
            this.proxy.getSearchResults("select * from comics where id=3"));
    }
    
    @Test
    void testLogoutVuelveABloquear() {
        this.proxy.login(correctPassword);
        this.proxy.logout();
        
        assertThrows(RuntimeException.class, () -> {
            this.proxy.getSearchResults("select * from comics where id=1");
        });
    }
}