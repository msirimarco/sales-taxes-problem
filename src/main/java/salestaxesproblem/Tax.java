package salestaxesproblem;

public interface Tax {

    boolean isApplicable(Product product);

    float taxFor(Product product, float price);
}
