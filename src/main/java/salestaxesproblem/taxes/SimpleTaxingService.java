package salestaxesproblem.taxes;

import salestaxesproblem.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class SimpleTaxingService implements TaxingService {

    private List<Tax> taxes;

    public SimpleTaxingService(){
        taxes = new ArrayList<Tax>();
    }

    @Override
    public float taxFor(Product product, float price) {
        float total = 0;
        for (Tax tax: taxes) {
            total += tax.isApplicable(product) ? tax.taxFor(product, price) : 0;
        }
        return  total;
    }

    @Override
    public void addTax(Tax tax) {
        taxes.add(tax);
    }
}
