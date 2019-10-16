package GeekBurger;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/**
 * Product class has only its name and the quantity of its ingredients,
 */

class Product implements java.io.Serializable {
    final String name;
    final Hashtable<Ingredient,Integer> ingredients;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        if (!name.equals(((Product) o).name)) return false;
        return ingredients.equals(((Product) o).ingredients);
    }

    @Override
    public int hashCode() {
        return 31 * name.hashCode() + ingredients.hashCode();
    }
}
