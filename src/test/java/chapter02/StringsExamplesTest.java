package chapter02;

import com.google.common.base.Strings;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class StringsExamplesTest {

    @Test
    public void testPad() {

        // boilerplate code to append: 5 lines of code
        StringBuilder builder = new StringBuilder("foo");
        char x = 'x';
        for (int i = 0; i < 3; i++) {
            builder.append(x);
        }

        Assert.assertThat(builder.toString(), Is.is("fooxxx"));

        // Using padEnd: one line of code. The second parameter is the minimum lenght of
        // the returned string
        String result = Strings.padEnd("foo", 6, 'x');

        Assert.assertThat(result, Is.is("fooxxx"));

    }

    @Test
    public void testNullToEmpty() {
        String emptyString1 = Strings.nullToEmpty("");
        String emptyString2 = Strings.nullToEmpty(null);
        String nonEmptyString1 = Strings.nullToEmpty(" ");
        String nonEmptyString2 = Strings.nullToEmpty("foo");

        Assert.assertThat(emptyString1, Is.is(""));
        Assert.assertThat(emptyString2, Is.is(""));
        Assert.assertThat(nonEmptyString1, Is.is(" "));
        Assert.assertThat(nonEmptyString2, Is.is("foo"));

    }

    @Test
    public void testEmptyToNull() {
        String nullString1 = Strings.emptyToNull(null);
        String nullString2 = Strings.emptyToNull("");
        String nonNullString1 = Strings.emptyToNull(" ");
        String nonNullString2 = Strings.emptyToNull("foo");

        Assert.assertNull(nullString1);
        Assert.assertNull(nullString2);
        Assert.assertThat(nonNullString1, Is.is(" "));
        Assert.assertThat(nonNullString2, Is.is("foo"));
    }

    @Test
    public void testIsNullOrEmpty() {
        boolean nullOrEmpty1 = Strings.isNullOrEmpty(null);
        boolean nullOrEmpty2 = Strings.isNullOrEmpty("");
        boolean nullOrEmpty3 = Strings.isNullOrEmpty(" ");
        boolean nullOrEmpty4 = Strings.isNullOrEmpty("foo");

        Assert.assertTrue(nullOrEmpty1);
        Assert.assertTrue(nullOrEmpty2);
        Assert.assertFalse(nullOrEmpty3);
        Assert.assertFalse(nullOrEmpty4);
    }

}
