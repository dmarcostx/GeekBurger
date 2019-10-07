import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

class Order implements java.io.Serializable {
    private final Client client;
    private final ArrayList<Product> products;
    private final Payment payment;
    private static final long serialVersionUID = 2801938148307690202L;

    Order(Client client, Collection<? extends Product> products, Payment payment) {
        this.client = client;
        this.products = new ArrayList<>(products);
        this.payment = payment;
    }

    Client getClient() {
        return client;
    }

    ArrayList<Product> getProducts() {
        return products;
    }

    public static void main(String[] args) {
        try(final var server = new ServerSocket(3302)) {
            while(true) {
                final var socket = server.accept();
                new Thread(() -> {
                    try {
                        final var order = (Order) new ObjectInputStream(socket.getInputStream()).readObject();
                        socket.close();
                        if(!order.payment.process())
                            return;
                        final var productionOutputStream = new ObjectOutputStream(new Socket("localhost",3303).getOutputStream());
                        productionOutputStream.writeObject(new Production(order));
                        productionOutputStream.flush();
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
