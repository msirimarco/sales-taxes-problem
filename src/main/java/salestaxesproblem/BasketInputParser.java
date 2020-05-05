package salestaxesproblem;

import salestaxesproblem.domain.Basket;
import salestaxesproblem.domain.Product;
import salestaxesproblem.domain.ProductType;
import salestaxesproblem.taxes.TaxingService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class BasketInputParser {

    private static final String BASKET_LINE_REGEX = "(\\d+) ([\\w\\s]* )at (\\d+.\\d{2})";

    public Basket createBasketFromFile(String fileName, TaxingService taxingService, ReceiptPrinter receiptPrinter) {
        Basket basket = new Basket(taxingService, receiptPrinter);
        String currentLine;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((currentLine = br.readLine()) != null)
            {
                Pattern pattern = Pattern.compile(BASKET_LINE_REGEX);
                Matcher matcher = pattern.matcher(currentLine);
                matcher.find();
                int quantity = Integer.parseInt(matcher.group(1));
                String name = matcher.group(2);
                boolean imported = false;
                if (name.contains("imported")) {
                    imported = true;
                    name = name.replaceAll("(imported)\\s+", "");
                }
                float price = Float.parseFloat(matcher.group(3));
                String finalName = name;
                ProductType type = ProductType.OTHER;
                if (name.contains("book")) {
                  type = ProductType.BOOK;
                } else if (name.contains("pill")) {
                    type = ProductType.MEDICAL;
                } else if (name.contains("chocolate")) {
                    type = ProductType.FOOD;
                }
                Product product = new Product(name, type, imported);
                basket.addProduct(product, price, quantity);
            }
            br.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return basket;
    }
}
