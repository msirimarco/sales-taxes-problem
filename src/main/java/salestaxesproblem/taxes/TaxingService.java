package salestaxesproblem.taxes;

import salestaxesproblem.domain.Product;

public interface TaxingService {
    float taxFor(Product product, float price);
    void addTax(Tax tax);
}
