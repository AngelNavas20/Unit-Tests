
public class NotificadorEmail {
    private final EmailCliente emailCliente;

    public NotificadorEmail(EmailCliente emailCliente) {
        this.emailCliente = emailCliente;
    }

    public void notificar(String direccion, String mensaje, String asunto) {
        if (direccion == null || direccion.isEmpty() || mensaje == null || mensaje.isEmpty() || asunto == null || asunto.isEmpty()) {
            throw new IllegalArgumentException("Dirección, mensaje y asunto no pueden ser nulos o vacíos");
        }
        emailCliente.enviarCorreo(direccion, mensaje, asunto);
    }
}

