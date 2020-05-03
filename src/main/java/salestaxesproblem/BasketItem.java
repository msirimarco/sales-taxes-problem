package salestaxesproblem;

public class BasketItem {

    private Product product;
    private float price;
    private int quantity;

    public BasketItem(Product product, float price, int quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public float getBasePrice() {
        return price*quantity;
    }
}
