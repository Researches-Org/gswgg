package chapter02;

import com.google.common.base.CharMatcher;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class CharMatcherExamplesTest {

    @Test
    public void testConvertStringToOneLine() {

        String multipleLine = "foo bar baz\nfoo bar baz";

        String result = CharMatcher.breakingWhitespace().replaceFrom(multipleLine, ' ');

        Assert.assertThat(result, Is.is("foo bar baz foo bar baz"));

    }

    @Test
    public void testRemoveWhiteSpace() {
        String tabsAndSpaces = " String  with    spaces  and     tabs ";

        String expected = "#String#with#spaces#and#tabs#";

        String scrubbed = CharMatcher.whitespace().collapseFrom(tabsAndSpaces, '#');

        Assert.assertThat(scrubbed, Is.is(expected));
    }

    @Test
    public void testTrimRemoveWhiteSpace() {
        String tabsAndSpaces = " String  with    spaces  and     tabs ";

        String expected = "String#with#spaces#and#tabs";

        String scrubbed = CharMatcher.whitespace().trimAndCollapseFrom(tabsAndSpaces, '#');

        Assert.assertThat(scrubbed, Is.is(expected));
    }

    @Test
    public void testRetainFrom() {
        String lettersAndNumbers = "foo989yxbar234";

        String expected = "989234";

        String retained = CharMatcher.javaDigit().retainFrom(lettersAndNumbers);

        Assert.assertThat(retained, Is.is(expected));
    }

    @Test
    public void testCombineCharMatcher() {
        CharMatcher charMatcher = CharMatcher.javaDigit().or(CharMatcher.whitespace());

        String lettersNumbersAndSpaces = "foo  989 bar234   baz  ";

        String expected = "foobarbaz";

        String result = charMatcher.removeFrom(lettersNumbersAndSpaces);

        Assert.assertThat(result, Is.is(expected));
    }

}
