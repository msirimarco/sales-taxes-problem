package salestaxesproblem;

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
            return price * rate;
        }
        return 0;
    }
}
