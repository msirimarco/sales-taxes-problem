package salestaxesproblem;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BasketShould {

    @Mock
    TaxingService taxingService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void computeTotalAmount()
    {
        Product product = new Product("Name", ProductType.BOOK, false);
        BasketItem item = new BasketItem(product, 12.49f, 2 );
        Product product2 = new Product("Name2", ProductType.OTHER, true);
        BasketItem item2 = new BasketItem(product, 50.50f, 2 );
        Basket basket = new Basket(taxingService);
        basket.addItem(item);
        basket.addItem(item2);
        assertThat(basket.getSalesTaxes(), is(0f));
    }

    @Test
    public void computeSalesTaxesItem() {
        Product product = new Product("Name", ProductType.BOOK, false);
        BasketItem item = new BasketItem(product, 12.49f, 2 );
        Basket basket = new Basket(taxingService);
        basket.addItem(item);
        assertThat(basket.getSalesTaxes(), is(0f));

    }
}
