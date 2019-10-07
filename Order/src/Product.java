import java.util.Hashtable;
import java.util.Map;

/**
 * Product class has only its name, the quantity of its ingredients,
 * and its specifics dietary restrictions.
 */

class Product implements java.io.Serializable {
    private final String name;
    private final Hashtable<Ingredient,Integer> ingredients;
    private static final long serialVersionUID = 660381795004299088L;

    Product(String name, Map<? extends Ingredient, ? extends Integer> ingredients) {
        this.name = name;
        this.ingredients = new Hashtable<>(ingredients);
    }
}
