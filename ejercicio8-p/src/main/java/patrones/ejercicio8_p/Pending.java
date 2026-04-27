package patrones.ejercicio8_p;

import java.time.Duration;
import java.time.LocalDateTime;

public class Pending extends EstadoToDoItem {
    @Override
    public void start(ToDoItem context) {
        context.setStartTime(LocalDateTime.now());
        context.setState(new InProgress());
    }

    @Override
    public Duration workedTime(ToDoItem context) {
        throw new RuntimeException("El ToDoItem no se inició");
    }
}

