package salestaxesproblem;

public class SimpleTaxingService implements TaxingService {

    private final float BASIC_SALES_TAX = .1f;
    private final float IMPORT_DUTY_TAX = .05f;

    @Override
    public float taxFor(BasketItem item) {
        return 0;
    }
}
