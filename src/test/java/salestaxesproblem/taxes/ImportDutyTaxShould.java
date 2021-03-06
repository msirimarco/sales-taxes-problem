package salestaxesproblem.taxes;

import org.junit.Before;
import org.junit.Test;
import salestaxesproblem.domain.Product;
import salestaxesproblem.domain.ProductType;
import salestaxesproblem.taxes.ImportDutyTax;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

public class ImportDutyTaxShould {

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
        assertThat(importDutyTax.taxFor(importedProduct, 15.69f), is(0.80f));
        assertThat(importDutyTax.taxFor(notImportedProduct, 15f), is(0f));
    }
}
