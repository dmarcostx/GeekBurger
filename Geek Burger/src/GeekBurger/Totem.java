package GeekBurger;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;

class Totem {

    private static final JFrame frame = new JFrame("Geek Burger");

    static void picture(ActionEvent actionEvent) {
        try {
            frame.setVisible(false);
            final var socket = new Socket("localhost",3300);
            final var facialRecognitionOutputStream = new ObjectOutputStream(socket.getOutputStream());
            facialRecognitionOutputStream.writeObject(new Image());
            facialRecognitionOutputStream.flush();
            filteredMenu((User) new ObjectInputStream(socket.getInputStream()).readObject());
            socket.close();
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println("Contact support");
        }
    }

    @SuppressWarnings("unchecked")
    private static void filteredMenu(final User user) throws IOException, ClassNotFoundException {
        final var socket = new Socket("localhost",3301);
        final var userOutputStream = new ObjectOutputStream(socket.getOutputStream());
        userOutputStream.writeObject(user);
        userOutputStream.flush();
        final Menu menu = new Menu((ArrayList<? extends Product>) new ObjectInputStream(socket.getInputStream()).readObject());
        menu.getLabel().setText("Hello "+user.name+", what's your choice for today?");
        frame.setContentPane(menu.getPanel());
        frame.setVisible(true);
    }

    static void product(ActionEvent e){
        try {
            frame.setVisible(false);
            final var socket = new Socket("localhost",3302);
            final ObjectOutputStream orderOutputStream;
            orderOutputStream = new ObjectOutputStream(socket.getOutputStream());
            final var ingredients = new Hashtable<Ingredient,Integer>(3,1);
            ingredients.put(new Ingredient("Hamburger"),2);
            ingredients.put(new Ingredient("Cheese"),2);
            ingredients.put(new Ingredient("Bread"),2);
            final var products = new ArrayList<Product>(1);
            products.add(new Product("X Burger",ingredients));
            orderOutputStream.writeObject(new Order(new Client("Maria"),products,new Payment()));
            orderOutputStream.flush();
            frame.setContentPane(new Thanks().getPanel());
            frame.setVisible(true);
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println("Contact support");
        }
    }

    public static void main(String[] args) {
        frame.setContentPane(new Welcome().getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
