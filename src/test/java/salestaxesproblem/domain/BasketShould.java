package salestaxesproblem.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import salestaxesproblem.ReceiptPrinter;
import salestaxesproblem.taxes.TaxingService;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;

@RunWith(MockitoJUnitRunner.class)
public class BasketShould {

    @Mock TaxingService taxingService;
    @Mock ReceiptPrinter receiptPrinter;

    private Product book;
    private Product perfume;
    private Basket basket;

    @Before
    public void setUp() throws Exception {
        book = new Product("Book", ProductType.BOOK, false);
        perfume = new Product("Perfume", ProductType.OTHER, false);
        when(taxingService.taxFor(any(Product.class), anyFloat())).thenReturn(5f);
        basket = new Basket(taxingService, receiptPrinter);
    }

    @Test
    public void updateSalesTaxesForAddedItem() {
        assertThat(basket.getSalesTaxes(), is(0f));
        basket.addProduct(book, 15f, 1);
        assertThat(basket.getSalesTaxes(), is(5f));
    }

    @Test
    public void updateTotalPriceForAddedItem() {
        assertEquals(0f, basket.getTotalPrice(), 0.0);
        basket.addProduct(book, 15f, 1);
        assertThat(basket.getTotalPrice(), is(20f));
    }

    @Test
    public void updateSalesTaxForAddedItemWithMoreQuantity() {
        assertThat(basket.getSalesTaxes(), is(0f));
        basket.addProduct(book, 15f, 3);
        assertThat(basket.getSalesTaxes(), is(3*5f));
    }

    @Test
    public void updateTotalPriceForAddedItemWithMoreQuantity() {
        assertThat(basket.getTotalPrice(), is(0f));
        basket.addProduct(book, 15f, 3);
        assertThat(basket.getTotalPrice(), is(3*(15f + 5f)));
    }
}
