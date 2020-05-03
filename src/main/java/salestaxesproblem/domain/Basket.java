package salestaxesproblem.domain;

import salestaxesproblem.taxes.TaxingService;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private TaxingService taxingService;
    private List<BasketItem> items;

    public Basket(TaxingService taxingService) {
        this.taxingService = taxingService;
        this.items = new ArrayList<BasketItem>();
    }

    public void addItem(BasketItem item) {
        items.add(item);
    }

    public float getSalesTaxes() {
        return 0f;
    }
}
