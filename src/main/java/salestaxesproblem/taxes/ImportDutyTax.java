package salestaxesproblem.taxes;

import salestaxesproblem.domain.Product;
import salestaxesproblem.utils.MathUtil;

public class ImportDutyTax implements Tax {

    private float rate;

    public ImportDutyTax(float rate){
        this.rate = rate;
    }

    @Override
    public boolean isApplicable(Product product) {
        return product.isImported();
    }

    @Override
    public float taxFor(Product product, float price) {
        if (isApplicable(product))
        {
            return MathUtil.roundUpToFraction(price * rate, 20);
        }
        return 0;
    }
}
