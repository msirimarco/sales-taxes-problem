package salestaxesproblem.taxes;

import org.junit.Before;
import org.junit.Test;
import salestaxesproblem.domain.Product;
import salestaxesproblem.domain.ProductType;
import salestaxesproblem.taxes.BasicSalesTax;

import java.util.EnumSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

public class BasicSalesTaxShould {

    private Product book;
    private Product pills;
    private Product beefSteak;
    private Product beer;

    @Before
    public void setUp() throws Exception {
        book = new Product("BOOK", ProductType.BOOK, false);
        pills = new Product("NotImportedProductName", ProductType.MEDICAL, false);
        beefSteak = new Product("NotImportedProductName", ProductType.FOOD, false);
        beer = new Product("NotImportedProductName", ProductType.OTHER, false);
    }

    @Test
    public void verifyIfApplicableToAProduct() {
        BasicSalesTax basicSalesTax = new BasicSalesTax(EnumSet.of(ProductType.OTHER), 0.1f);
        assertTrue(basicSalesTax.isApplicable(beer));
        assertFalse(basicSalesTax.isApplicable(book));
        assertFalse(basicSalesTax.isApplicable(pills));
        assertFalse(basicSalesTax.isApplicable(beefSteak));
    }

    @Test
    public void returnTaxAmount() {
        BasicSalesTax basicSalesTax = new BasicSalesTax(EnumSet.of(ProductType.OTHER), 0.1f);
        assertThat(basicSalesTax.taxFor(beer, 3.59f), is(0.40f));
        assertThat(basicSalesTax.taxFor(book, 15f), is(0f));
    }
}
