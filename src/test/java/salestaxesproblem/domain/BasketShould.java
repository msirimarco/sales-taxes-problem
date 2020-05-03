package salestaxesproblem.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import salestaxesproblem.taxes.TaxingService;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;

@RunWith(MockitoJUnitRunner.class)
public class BasketShould {

    @Mock
    TaxingService taxingService;

    private BasketItem bookBasketItem;
    private BasketItem perfumeBasketItem;
    private BasketItem twoBookBasketItem;
    private Basket basket;

    @Before
    public void setUp() throws Exception {
        Product book = new Product("Book", ProductType.BOOK, false);
        Product perfume = new Product("Perfume", ProductType.OTHER, false);
        bookBasketItem = new BasketItem(book, 15f, 1);
        perfumeBasketItem = new BasketItem(perfume, 50f, 1);
        twoBookBasketItem = new BasketItem(book, 15f, 3);
        when(taxingService.taxFor(any(Product.class), anyFloat())).thenReturn(5f);
        basket = new Basket(taxingService);
    }

    @Test
    public void updateSalesTaxesForAddedItem() {
        assertThat(basket.getSalesTaxes(), is(0f));
        basket.addItem(bookBasketItem);
        assertThat(basket.getSalesTaxes(), is(5f));
    }

    @Test
    public void updateTotalPriceForAddedItem() {
        assertEquals(0f, basket.getTotalPrice(), 0.0);
        basket.addItem(bookBasketItem);
        assertThat(basket.getTotalPrice(), is(20f));
    }

    @Test
    public void updateSalesTaxForAddedItemWithMoreQuantity() {
        assertThat(basket.getSalesTaxes(), is(0f));
        basket.addItem(twoBookBasketItem);
        assertThat(basket.getSalesTaxes(), is(3*5f));
    }

    @Test
    public void updateTotalPriceForAddedItemWithMoreQuantity() {
        assertThat(basket.getTotalPrice(), is(0f));
        basket.addItem(twoBookBasketItem);
        assertThat(basket.getTotalPrice(), is(3*(15f + 5f)));
    }
}
