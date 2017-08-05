package chapter02;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JoinerExamplesTest {

    @Test
    public void testBuildStringWithNullsPresent() {

        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add(null);
        stringList.add("b");

        String expected = "a|b";

        Assert.assertThat(JoinerExamples.buildString(stringList, "|"), Is.is(expected));

    }

    @Test
    public void testBuildStringWithJoinerWithNullsPresent() {
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add(null);
        stringList.add("b");

        String expected = "a|b";

        Assert.assertThat(JoinerExamples.buildStringWithJoiner(stringList, "|"), Is.is(expected));
    }

}
