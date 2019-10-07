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
}
