package salestaxesproblem.domain;

import salestaxesproblem.ReceiptPrinter;
import salestaxesproblem.taxes.TaxingService;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private TaxingService taxingService;
    private ReceiptPrinter receiptPrinter;
    private List<BasketItem> items;
    private float totalPrice;
    private float salesTaxes;

    public Basket(TaxingService taxingService, ReceiptPrinter receiptPrinter) {
        this.taxingService = taxingService;
        this.receiptPrinter = receiptPrinter;
        this.items = new ArrayList<BasketItem>();
        this.salesTaxes = 0f;
        this.totalPrice = 0f;
    }

    public void addProduct(Product product, float price, int quantity) {
        float taxes = taxingService.taxFor(product, price);
        BasketItem item = new BasketItem(product, price, quantity, taxes);
        items.add(item);
        totalPrice += (price + taxes) * quantity;
        salesTaxes += taxes * quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public float getSalesTaxes() {
        return salesTaxes;
    }


    public void printReceipt() {
        for (BasketItem item : items) {
            float priceAndTaxes = item.getTotalPrice() + item.getTotalTaxes();
            receiptPrinter.printLine(String.format("%d %s: %.2f", item.getQuantity(), item.getProduct().getName(), priceAndTaxes));
        }
        receiptPrinter.printLine( String.format("Sales Taxes: %.2f", salesTaxes));
        receiptPrinter.printLine( String.format("Total: %.2f", totalPrice));
    }
}
