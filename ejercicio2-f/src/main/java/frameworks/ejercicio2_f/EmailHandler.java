package frameworks.ejercicio2_f;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class EmailHandler extends Handler {
    private String destinatario;

    public EmailHandler(String destinatario) {
        this.destinatario = destinatario;
    }

    @Override
    public void publish(LogRecord record) {
        if (!isLoggable(record)) return;

        // ¡Clave! Usamos el Formatter que nos hayan configurado (JSON o Mayúsculas)
        String formattedMessage = getFormatter().format(record);
        String subject = "Alerta de Sistema: " + record.getLevel().getName();

        enviarMail(this.destinatario, subject, formattedMessage);
    }

    private void enviarMail(String to, String subject, String body) {
        // Aquí iría el código real del anexo usando javax.mail.
        // Para probar que funciona, simulamos el envío en consola:
        System.out.println("📧 [ENVIANDO EMAIL A " + to + "]");
        System.out.println("Asunto: " + subject);
        System.out.println("Cuerpo: " + body);
        System.out.println("-----------------------------------");
    }

    @Override
    public void flush() {}

    @Override
    public void close() throws SecurityException {}
}
