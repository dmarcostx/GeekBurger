package GeekBurger;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collection;

class User implements java.io.Serializable {
    private final int id;
    final String name;
    private final ArrayList<Restriction> restrictions;
    private static final long serialVersionUID = -2007774907789007245L;

    User(int id, String name, Collection<? extends Restriction> restrictions) {
        this.id = id;
        this.name = name;
        this.restrictions = new ArrayList<>(restrictions);
    }

    ArrayList<Product> filter(Collection<? extends Product> products) {
        for (Restriction restriction : restrictions)
            products = restriction.filter(products);
        return new ArrayList<>(products);
    }

    public static void main(String[] args) {
        new Thread(facialRecognition::main).start();
        try(final var server = new ServerSocket(3301)) {
            while(true) {
                final var socket = server.accept();
                new Thread(() -> {
                    try {
                        final var user = (User) new ObjectInputStream(socket.getInputStream()).readObject();
                        final var visualInterfaceOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        visualInterfaceOutputStream.writeObject(user.filter(Product.getMenu()));
                        visualInterfaceOutputStream.flush();
                        socket.close();
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