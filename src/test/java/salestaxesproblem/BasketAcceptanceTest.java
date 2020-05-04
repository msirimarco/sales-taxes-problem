package salestaxesproblem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import salestaxesproblem.domain.Basket;
import salestaxesproblem.domain.BasketItem;
import salestaxesproblem.domain.Product;
import salestaxesproblem.domain.ProductType;
import salestaxesproblem.taxes.BasicSalesTax;
import salestaxesproblem.taxes.ImportDutyTax;
import salestaxesproblem.taxes.SimpleTaxingService;
import salestaxesproblem.taxes.TaxingService;

import java.util.EnumSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BasketAcceptanceTest {

    @Mock ReceiptPrinter receiptPrinter;

    private Basket basket;

    @Before
    public void setUp() throws Exception {
        TaxingService taxingService = new SimpleTaxingService();
        taxingService.addTax(new ImportDutyTax(.05f));
        taxingService.addTax(new BasicSalesTax(EnumSet.of(ProductType.OTHER), .1f));
        basket = new Basket(taxingService, receiptPrinter);
    }

    @Test
    public void printedReceiptContainsAllItems1() {
        Product book = new Product("book", ProductType.BOOK, false);
        Product musicCd = new Product("music CD", ProductType.OTHER, false);
        Product chocolateBar = new Product("chocolate bar", ProductType.FOOD, false);
        basket.addProduct(book, 12.49f, 2);
        basket.addProduct(musicCd, 14.99f, 1);
        basket.addProduct(chocolateBar, 0.85f, 1);
        basket.printReceipt();
        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        verify(receiptPrinter, times(5)).printLine(stringCaptor.capture());
        List<String> capturedString = stringCaptor.getAllValues();
        assertEquals("2 book: 24.98", capturedString.get(0));
        assertEquals("1 music CD: 16.49", capturedString.get(1));
        assertEquals("1 chocolate bar: 0.85", capturedString.get(2));
        assertEquals("Sales Taxes: 1.50", capturedString.get(3));
        assertEquals("Total: 42.32", capturedString.get(4));
    }

    @Test
    public void printedReceiptContainsAllItems2() {
        Product importedBoxOfChocolate = new Product("imported box of chocolates", ProductType.FOOD, true);
        Product importedBottleOfPerfume = new Product("imported bottle of perfume", ProductType.OTHER, true);
        basket.addProduct(importedBoxOfChocolate, 10f, 1);
        basket.addProduct(importedBottleOfPerfume, 47.50f, 1);
        basket.printReceipt();
        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        verify(receiptPrinter, times(4)).printLine(stringCaptor.capture());
        List<String> capturedString = stringCaptor.getAllValues();
        assertEquals("1 imported box of chocolates: 10.50", capturedString.get(0));
        assertEquals("1 imported bottle of perfume: 54.65", capturedString.get(1));
        assertEquals("Sales Taxes: 7.65", capturedString.get(2));
        assertEquals("Total: 65.15", capturedString.get(3));
    }

    @Test
    public void printedReceiptContainsAllItems3() {
        Product importedBottleOfPerfume = new Product("imported bottle of perfume", ProductType.OTHER, true);
        basket.addProduct(importedBottleOfPerfume, 27.99f, 1);

        Product bottleOfPerfume = new Product("bottle of perfume", ProductType.OTHER, false);
        basket.addProduct(bottleOfPerfume, 18.99f, 1);

        Product packetOfHeadachePills = new Product("packet of headache pills", ProductType.MEDICAL, false);
        basket.addProduct(packetOfHeadachePills, 9.75f, 1);

        Product boxOfImportedChocolates = new Product("box of imported chocolates", ProductType.FOOD, true);
        basket.addProduct(boxOfImportedChocolates, 18.99f, 3);

        basket.printReceipt();
        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        verify(receiptPrinter, times(6)).printLine(stringCaptor.capture());
        List<String> capturedString = stringCaptor.getAllValues();
        assertEquals("1 imported bottle of perfume: 32.19", capturedString.get(0));
        assertEquals("1 bottle of perfume: 20.89", capturedString.get(1));
        assertEquals("1 packet of headache pills: 9.75", capturedString.get(2));
        assertEquals("3 imported box of chocolates: 35.55", capturedString.get(3));
        assertEquals("Sales Taxes: 7.90", capturedString.get(4));
        assertEquals("Total: 98.38", capturedString.get(5));
    }

}
