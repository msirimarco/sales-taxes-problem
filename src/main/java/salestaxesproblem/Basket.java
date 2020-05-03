package salestaxesproblem;

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
        float totalSalesTaxes = 0;
        for(BasketItem item : items)
        {
            totalSalesTaxes += taxingService.taxFor(item);
        }
        return totalSalesTaxes;
    }
}
