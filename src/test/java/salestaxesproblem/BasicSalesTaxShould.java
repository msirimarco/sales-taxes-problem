package salestaxesproblem;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

public class BasicSalesTaxShould {

    private Product importedProduct;
    private Product notImportedProduct;

    @Before
    public void setUp() throws Exception {
        importedProduct = new Product("ImportedProductName", ProductType.OTHER, true);
        notImportedProduct = new Product("NotImportedProductName", ProductType.OTHER, false);
    }

    @Test
    public void verifyIfApplicableToAProduct() {
        ImportDutyTax importDutyTax = new ImportDutyTax(0.05f);
        assertTrue(importDutyTax.isApplicable(importedProduct));
        assertFalse(importDutyTax.isApplicable(notImportedProduct));
    }

    @Test
    public void returnTaxAmount() {
        ImportDutyTax importDutyTax = new ImportDutyTax(0.05f);
        assertThat(importDutyTax.taxFor(importedProduct, 15f), is(0.75f));
        assertThat(importDutyTax.taxFor(notImportedProduct, 15f), is(0f));
    }
}
