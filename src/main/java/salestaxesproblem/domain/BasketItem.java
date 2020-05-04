package salestaxesproblem.domain;

public class BasketItem {

    private Product product;
    private float price;
    private int quantity;
    private float taxes;

    public BasketItem(Product product, float price, int quantity, float taxes) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.taxes = taxes;
    }

    public Product getProduct() {
        return product;
    }

    public float getPrice() {
        return price;
    }

    public float getTotalPrice() {
        return price * quantity;
    }

    public float getTaxes() {
        return taxes;
    }

    public float getTotalTaxes() {
        return taxes * quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
