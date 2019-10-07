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

    Production(Order order) {
        this.client = order.getClient();
        this.products = new ArrayList<>(order.getProducts());
    }
}
