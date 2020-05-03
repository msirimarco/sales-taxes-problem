package salestaxesproblem;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleTaxingServiceShould {
    @Test
    public void returnZeroTaxesForANotImportedBook() {
        Product product = new Product("Product", ProductType.BOOK, false);
        BasketItem basketItem = new BasketItem(product, 15f, 1);
        SimpleTaxingService taxingService = new SimpleTaxingService();
        float tax = taxingService.taxFor(basketItem);
        assertThat(tax, is(0f));
    }

    @Test
    public void applyImportDutyTaxToImportedProduct() {
        Product product = new Product("Product", ProductType.BOOK, true);
        BasketItem basketItem = new BasketItem(product, 15f, 1);
        SimpleTaxingService taxingService = new SimpleTaxingService();
    }
}
