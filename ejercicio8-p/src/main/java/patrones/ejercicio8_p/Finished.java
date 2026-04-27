package patrones.ejercicio8_p;

import java.time.Duration;

public class Finished extends EstadoToDoItem {
    @Override
    public Duration workedTime(ToDoItem context) {
        // En finished, el tiempo es desde que inició hasta que finalizó
        return Duration.between(context.getStartTime(), context.getEndTime());
    }

    @Override
    public void addComment(ToDoItem context, String comment) {
        // En finished no se hace nada al agregar un comentario
    }
}