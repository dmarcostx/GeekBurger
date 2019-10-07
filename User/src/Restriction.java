import java.util.ArrayList;
import java.util.Collection;

/**
 * Restriction class has the name of the dietary restriction, a list of restricted products,
 * and a list of restricted ingredients.
 * Note that, to avoid unnecessary redundancy, the product list must have all the products
 * that are restricted, except those who are restricted only because of its ingredients.
 */
class Restriction implements java.io.Serializable {
    private final String name;
    private final ArrayList<Product> products;
    private final ArrayList<Ingredient> ingredients;
    private static final long serialVersionUID = 7335291665374872553L;

    Restriction(String name, Collection<? extends Product> products, Collection<? extends Ingredient> ingredients) {
        this.name = name;
        this.products = new ArrayList<>(products);
        this.ingredients = new ArrayList<>(ingredients);
    }

    ArrayList<Product> filter(final Collection<? extends Product> products) {
        final var filtered = new ArrayList<Product>(products);
        products.forEach(product -> {
            if (this.products.contains(product))
                filtered.remove(product);
            else
                product.getIngredients().keySet().forEach(ingredient -> {
                    if (this.ingredients.contains(ingredient)){
                        filtered.remove(product);}
                });
        });
        return filtered;
    }
}
