package salestaxesproblem.utils;

public class MathUtil {
    public static float roundUpToFraction(float number, int fraction) {
        return (float) Math.ceil(number*fraction) / fraction;
    }
}
