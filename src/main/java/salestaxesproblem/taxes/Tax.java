package salestaxesproblem.taxes;

import salestaxesproblem.domain.Product;

public interface Tax {

    boolean isApplicable(Product product);

    float taxFor(Product product, float price);
}
