package salestaxesproblem.taxes;

import salestaxesproblem.domain.Product;
import salestaxesproblem.domain.ProductType;
import salestaxesproblem.utils.MathUtil;

import java.util.EnumSet;

public class BasicSalesTax implements Tax {

    private float rate;
    private EnumSet<ProductType> productTypes;

    public BasicSalesTax(EnumSet<ProductType> productTypes, float rate) {
        this.rate = rate;
        this.productTypes = productTypes;
    }

    @Override
    public boolean isApplicable(Product product) {
        return productTypes.contains(product.getType());
    }

    @Override
    public float taxFor(Product product, float price) {
        if (isApplicable(product)) {
            return MathUtil.roundUpToFraction(price * rate, 20);
        }
        return 0;
    }
}
