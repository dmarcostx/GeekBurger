package GeekBurger;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.ArrayList;

class facialRecognition {
    static boolean hasFace(Image image) {
        return true;
    }

    static User getUser(Image image) {
        final var restrictions = new ArrayList<Restriction>(2);
        var ingredient = new ArrayList<Ingredient>(1);
        ingredient.add(new Ingredient("Egg"));
        restrictions.add(new Restriction("Egg",new ArrayList<>(0),ingredient));
        ingredient = new ArrayList<>(1);
        ingredient.add(new Ingredient("Bacon"));
        restrictions.add(new Restriction("Bacon",new ArrayList<>(0),ingredient));
        return new User(1,"Maria", restrictions);
    }

    static void uploadToCloud(Image image) {}

    static void main() {
        try(final var server = new ServerSocket(3300)) {
            while(true) {
                final var socket = server.accept();
                new Thread(() -> {
                    try {
                        final var image = (Image) new ObjectInputStream(socket.getInputStream()).readObject();
                        if(hasFace(image)) {
                            final var visualInterfaceOutputStream = new ObjectOutputStream(socket.getOutputStream());
                            visualInterfaceOutputStream.writeObject(getUser(image));
                            visualInterfaceOutputStream.flush();
                        }
                        else
                            uploadToCloud(image);
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
