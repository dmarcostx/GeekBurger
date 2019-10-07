import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/**
 * Product class has only its name and the quantity of its ingredients,
 */

class Product implements java.io.Serializable {
    private final String name;
    private final Hashtable<Ingredient,Integer> ingredients;
    private static final long serialVersionUID = 660381795004299088L;

    Product(String name, Map<? extends Ingredient, ? extends Integer> ingredients) {
        this.name = name;
        this.ingredients = new Hashtable<>(ingredients);
    }

    String getName() {
        return name;
    }

    Hashtable<Ingredient, Integer> getIngredients() {
        return ingredients;
    }
}
