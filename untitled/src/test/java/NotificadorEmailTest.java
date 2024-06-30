
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class NotificadorEmailTest {

    @Mock
    private EmailCliente emailClienteMock;

    @Test
    public void testNotificar() {
        NotificadorEmail notificador = new NotificadorEmail(emailClienteMock);
        notificador.notificar("ejemplo@test.com", "Hola Mundo", "Hola");

        // Verificar que emailClienteMock.enviarCorreo se llamó con los argumentos correctos
        verify(emailClienteMock).enviarCorreo("ejemplo@test.com", "Hola Mundo", "Hola");
    }

    @Test
    public void testNotificarConDireccionVacia() {
        NotificadorEmail notificador = new NotificadorEmail(emailClienteMock);
        assertThrows(IllegalArgumentException.class, () -> {
            notificador.notificar("", "Mensaje", "Hola");
        });

        // Verificar que no se realiza el envío si la dirección es vacía
        verify(emailClienteMock, times(0)).enviarCorreo(anyString(), anyString(), anyString());
    }

    @Test
    public void testNotificarConAsuntoYMensajeNulo() {
        NotificadorEmail notificador = new NotificadorEmail(emailClienteMock);
        assertThrows(IllegalArgumentException.class, () -> {
            notificador.notificar("ejemplo@test.com", null, null);
        });

        // Verificar que no se realiza el envío si el mensaje es nulo
        verify(emailClienteMock, times(0)).enviarCorreo(anyString(), anyString(), anyString());
    }

    @Test
    public void testNotificarConMensajeNulo() {
        NotificadorEmail notificador = new NotificadorEmail(emailClienteMock);
        assertThrows(IllegalArgumentException.class, () -> {
            notificador.notificar("ejemplo@test.com", "Hello World", null);
        });

        // Verificar que no se realiza el envío si el asunto es nulo
        verify(emailClienteMock, times(0)).enviarCorreo(anyString(), anyString(), anyString());
    }

    @Test
    public void testNotificarConDireccionYMensajeValidos() {
        NotificadorEmail notificador = new NotificadorEmail(emailClienteMock);
        notificador.notificar("ejemplo@test.com", "Mensaje válido", "Hola");

        // Verificar que emailClienteMock.enviarCorreo se llamó con los argumentos correctos
        verify(emailClienteMock).enviarCorreo("ejemplo@test.com", "Mensaje válido", "Hola");
    }
}
