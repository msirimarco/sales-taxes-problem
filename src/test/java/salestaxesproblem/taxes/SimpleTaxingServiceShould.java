package salestaxesproblem.taxes;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import salestaxesproblem.domain.Product;
import salestaxesproblem.domain.ProductType;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SimpleTaxingServiceShould {

    @Mock
    Tax tax1, tax2;

    private SimpleTaxingService taxingService;
    private Product product;

    @Before
    public void setUp() throws Exception {
        when(tax1.isApplicable(any(Product.class))).thenReturn(true);
        when(tax2.isApplicable(any(Product.class))).thenReturn(true);
        when(tax1.taxFor(any(Product.class), anyFloat() )).thenReturn(5f);
        when(tax2.taxFor(any(Product.class), anyFloat() )).thenReturn(6f);
        taxingService = new SimpleTaxingService();
        product = new Product("ProductName", ProductType.OTHER, false);
    }

    @Test
    public void useTaxObjectToComputeTaxes() {
        taxingService.addTax(tax1);
        taxingService.addTax(tax2);
        assertThat(taxingService.taxFor(product, 15f), is(11f));
    }

}
