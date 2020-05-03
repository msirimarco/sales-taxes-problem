package salestaxesproblem;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class BasketItemShould {
    private Product product;

    @Before
    public void setUp() throws Exception {
        product = new Product("Name", ProductType.BOOK, false);
    }

    @Test
    public void computeAmountOfProduct() {
        BasketItem item = new BasketItem(product, 12.49f, 1);
        assertThat(item.getBasePrice(), is(12.49f));
    }

    @Test
    public void computeAmountOfMoreThanOneProduct() {
        BasketItem item = new BasketItem(product, 12.49f, 3);
        assertThat(item.getBasePrice(), is(12.49f*3));
    }

}
