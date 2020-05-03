package salestaxesproblem.domain;

public class Product {

    private String name;
    private ProductType type;
    private boolean imported;

    public Product(String name, ProductType type, boolean imported) {
        this.name = name;
        this.type = type;
        this.imported = imported;
    }

    public boolean isImported()
    {
        return imported;
    }

    public ProductType getType() {
        return type;
    }
}
