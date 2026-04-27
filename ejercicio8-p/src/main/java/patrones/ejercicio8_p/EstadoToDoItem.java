package patrones.ejercicio8_p;

import java.time.Duration;
import java.time.LocalDateTime;

// Se decidió que sea clase abstracta y no interfaz porque el enunciado dice que hay varias cosas en comun como los errores, 
// esto se puede aprovechar y que las clases concretas redefinan solo lo necesario.
public abstract class EstadoToDoItem {
    
    // Por defecto, start y finish no hacen nada si no estás en el estado correcto.
    public void start(ToDoItem context) {}
    public void finish(ToDoItem context) {}
    
    // Por defecto, togglePause explota (solo InProgress y Paused lo redefinen)
    public void togglePause(ToDoItem context) {
        throw new RuntimeException("El objeto ToDoItem no se encuentra en in-progress o paused");
    }
    
    // Por defecto, el tiempo trabajado es desde que arrancó hasta AHORA. 
    // (Pending y Finished lo redefinen con su propia lógica).
    public Duration workedTime(ToDoItem context) {
        return Duration.between(context.getStartTime(), LocalDateTime.now());
    }
    
    // Por defecto, cualquier estado deja agregar comentarios (Finished lo redefina a nada)
    public void addComment(ToDoItem context, String comment) {
        context.addCommentToList(comment);
    }
}