import java.util.ArrayList;
import java.util.Collection;

class User implements java.io.Serializable {
    private final int id;
    private final String name;
    private final ArrayList<Restriction> restrictions;
    private static final long serialVersionUID = -2007774907789007245L;

    User(int id, String name, Collection<? extends Restriction> restrictions) {
        this.id = id;
        this.name = name;
        this.restrictions = new ArrayList<>(restrictions);
    }

    User(int id, String name) {
        this.id = id;
        this.name = name;
        this.restrictions = new ArrayList<Restriction>(0);
    }

    ArrayList<Product> filter(final Collection<? extends Product> products) {
        restrictions.forEach(restriction -> restriction.filter(products));
        return new ArrayList<>(products);
    }

    String getName() {
        return name;
    }
}