import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

public class Production implements java.io.Serializable {
    private final Client client;
    private final ArrayList<Product> products;
    private static final long serialVersionUID = -546454976645958144L;

    Production(Client client, Collection<? extends Product> products) {
        this.client = client;
        this.products = new ArrayList<>(products);
    }

    Client getClient() {
        return client;
    }

    private boolean produce(){
        return true;
    }

    public static void main(String[] args) {
        try(final var server = new ServerSocket(3303)) {
            while(true) {
                final var socket = server.accept();
                new Thread(() -> {
                    try {
                        final var production = (Production) new ObjectInputStream(socket.getInputStream()).readObject();
                        socket.close();
                        production.produce();
                        final var notificationOutputStream = new ObjectOutputStream(new Socket("localhost",3304).getOutputStream());
                        notificationOutputStream.writeObject(production.client);
                        notificationOutputStream.flush();
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
