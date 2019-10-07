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

    static ArrayList<Product> getMenu() {
        final var menu = new ArrayList<Product>(4);
        var ingredients = new Hashtable<Ingredient,Integer>(7,1);
        ingredients.put(new Ingredient("Hamburger"),2);
        ingredients.put(new Ingredient("Cheese"),2);
        ingredients.put(new Ingredient("Bread"),2);
        menu.add(new Product("X Burger",ingredients));
        ingredients.put(new Ingredient("Tomato"),3);
        ingredients.put(new Ingredient("Lettuce"),1);
        menu.add(new Product("X Salad",ingredients));
        ingredients.put(new Ingredient("Bacon"),5);
        menu.add(new Product("X Bacon",ingredients));
        ingredients.put(new Ingredient("Egg"),1);
        menu.add(new Product("X Everything",ingredients));
        return menu;
    }

    String getName() {
        return name;
    }

    Hashtable<Ingredient, Integer> getIngredients() {
        return ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!name.equals(product.name)) return false;
        return ingredients.equals(product.ingredients);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + ingredients.hashCode();
        return result;
    }
}
