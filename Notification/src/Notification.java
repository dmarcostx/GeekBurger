import java.io.ObjectInputStream;
import java.net.ServerSocket;

class Notification {
    private final Client client;
    private final String message;

    Notification(Client client) {
        this.client = client;
        this.message = "Your order is ready";
    }

    private boolean send(){
        return true;
    }

    public static void main(String[] args) {
        try(final var server = new ServerSocket(3304)) {
            while(true) {
                final var socket = server.accept();
                new Thread(() -> {
                    try {
                        final var client = (Client) new ObjectInputStream(socket.getInputStream()).readObject();
                        socket.close();
                        new Notification(client).send();
                    } catch (final Throwable t) {
                        t.printStackTrace();
                        System.out.println("Contact support");
                    }
                }).start();
            }
        } catch (final Throwable t) {
            t.printStackTrace();
            System.out.println("Contact support");
        }
    }
}
