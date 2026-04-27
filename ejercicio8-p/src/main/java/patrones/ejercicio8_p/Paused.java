package patrones.ejercicio8_p;

import java.time.LocalDateTime;

public class Paused extends EstadoToDoItem {
    @Override
    public void togglePause(ToDoItem context) {
        context.setState(new InProgress());
    }

    @Override
    public void finish(ToDoItem context) {
        context.setEndTime(LocalDateTime.now());
        context.setState(new Finished());
    }
}