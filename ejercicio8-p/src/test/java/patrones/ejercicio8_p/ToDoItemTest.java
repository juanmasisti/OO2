package patrones.ejercicio8_p;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;

public class ToDoItemTest {

    private ToDoItem tarea;

    @BeforeEach
    public void setUp() {
        tarea = new ToDoItem("Implementar Patrón State");
    }

    @Test
    public void testEstadoInicialPending() {
        // Un item pendiente no tiene tiempo trabajado
        Exception exception = assertThrows(RuntimeException.class, () -> {
            tarea.workedTime();
        });
        assertEquals("El ToDoItem no se inició", exception.getMessage());
        
        // Pero permite comentarios
        tarea.addComment("Primer comentario");
        assertEquals(1, tarea.getComments().size());
    }

    @Test
    public void testStartAndFinish() throws InterruptedException {
        tarea.start();
        
        // Dejamos pasar un poquito de tiempo para simular trabajo
        Thread.sleep(50); 
        
        // togglePause desde in-progress no debería tirar error
        assertDoesNotThrow(() -> tarea.togglePause()); 
        
        // Pasamos de Paused a Finished directamente
        tarea.finish();
        
        Duration tiempoTrabajado = tarea.workedTime();
        assertTrue(tiempoTrabajado.toMillis() >= 50);

        // En finished no debería dejar agregar comentarios ni hacer togglePause
        tarea.addComment("Comentario tardío");
        assertEquals(0, tarea.getComments().size());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            tarea.togglePause();
        });
        assertEquals("El objeto ToDoItem no se encuentra en in-progress o paused", exception.getMessage());
    }
}