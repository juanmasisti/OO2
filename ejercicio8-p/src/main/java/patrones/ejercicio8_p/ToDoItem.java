package patrones.ejercicio8_p;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ToDoItem {
    private String name;
    private List<String> comments;
    private EstadoToDoItem state;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public ToDoItem(String name) {
        this.name = name;
        this.comments = new ArrayList<>();
        this.state = new Pending(); // Estado inicial
    }

    // --- Métodos del Protocolo (Delegación pura al estado) ---
    public void start() { state.start(this); }
    public void togglePause() { state.togglePause(this); }
    public void finish() { state.finish(this); }
    public Duration workedTime() { return state.workedTime(this); }
    public void addComment(String comment) { state.addComment(this, comment); }

    // --- Métodos internos para que los estados modifiquen el contexto ---
    protected void setState(EstadoToDoItem state) { this.state = state; }
    protected void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    protected LocalDateTime getStartTime() { return startTime; }
    protected void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    protected LocalDateTime getEndTime() { return endTime; }
    protected void addCommentToList(String comment) { this.comments.add(comment); }
    public List<String> getComments() { return this.comments; } // Para los tests
}