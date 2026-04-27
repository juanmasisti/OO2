package patrones.ejercicio8_p;

import java.time.LocalDateTime;

public class InProgress extends EstadoToDoItem {
    @Override
    public void togglePause(ToDoItem context) {
        context.setState(new Paused());
    }

    @Override
    public void finish(ToDoItem context) {
        context.setEndTime(LocalDateTime.now());
        context.setState(new Finished());
    }
}