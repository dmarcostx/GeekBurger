class Ingredient implements java.io.Serializable {
    private final String name;
    private static final long serialVersionUID = -1595107338663594532L;

    Ingredient(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredient that = (Ingredient) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
