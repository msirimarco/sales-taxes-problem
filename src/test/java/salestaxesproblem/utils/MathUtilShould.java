package salestaxesproblem.utils;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MathUtilShould {
    @Test
    public void roundUpToFraction() {
        assertThat(MathUtil.roundUpToFraction(5.6333334f, 20), is(5.65f));
        assertThat(MathUtil.roundUpToFraction(5.6633334f, 20), is(5.7f));
        assertThat(MathUtil.roundUpToFraction(0.1333334f, 20), is(0.15f));
        assertThat(MathUtil.roundUpToFraction(0f, 20), is(0f));
        assertThat(MathUtil.roundUpToFraction(0.15f, 20), is(0.15f));
    }
}
