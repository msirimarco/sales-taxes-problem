package salestaxesproblem.domain;

import salestaxesproblem.taxes.TaxingService;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private TaxingService taxingService;
    private List<BasketItem> items;
    private float totalPrice;
    private float salesTaxes;

    public Basket(TaxingService taxingService) {
        this.taxingService = taxingService;
        this.items = new ArrayList<BasketItem>();
        this.salesTaxes = 0f;
        this.totalPrice = 0f;
    }

    public void addItem(BasketItem item) {
        items.add(item);
        addNewItemPriceToTotal(item);
        addNewItemSaleTax(item);
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public float getSalesTaxes() {
        return salesTaxes;
    }

    private void addNewItemPriceToTotal(BasketItem item) {
        float unitPrice = item.getPrice() + taxingService.taxFor(item.getProduct(), item.getPrice());
        totalPrice += unitPrice * item.getQuantity();
    }

    private void addNewItemSaleTax(BasketItem item) {
        float unitTax = taxingService.taxFor(item.getProduct(), item.getPrice());
        salesTaxes += unitTax * item.getQuantity();
    }
}
