package patrones.ejercicio3_p;

public class VideoStreamAdapter implements Media {
    private VideoStream videoStream;

    // El adaptador recibe la instancia incompatible en su constructor
    public VideoStreamAdapter(VideoStream videoStream) {
        this.videoStream = videoStream;
    }

    // Cumple con el contrato de Media, pero traduce la llamada
    @Override
    public void play() {
        this.videoStream.reproduce();
    }
}
