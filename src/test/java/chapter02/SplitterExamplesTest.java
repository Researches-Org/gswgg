package chapter02;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SplitterExamplesTest {

    @Test
    public void testStringSplit() {
        String testString = "Monday,Tuesday,,Thursday,Friday,,";

        String[] parts = testString.split(",");

        Assert.assertThat(parts.length, Is.is(5));
        Assert.assertThat(parts, Is.is(new String[]{"Monday", "Tuesday", "", "Thursday", "Friday"}));

    }

    @Test
    public void testSplitter() {
        List<String> parts = new ArrayList<>();

        Splitter.on("|").split("foo|bar|baz").forEach(s -> parts.add(s));


        Assert.assertThat(parts, Is.is(Arrays.asList(new String[]{"foo", "bar", "baz"})));
    }

    @Test
    public void testImmutability() {
        Splitter splitter = Splitter.on("|");

        splitter.trimResults();

        List<String> parts = new ArrayList<>();

        splitter.split("1|2|3|||").forEach(s -> parts.add(s));

        Assert.assertThat(parts, Is.is(Arrays.asList(new String[]{"1", "2", "3", "", "", ""})));
    }

    @Test
    public void testMapSplitter() {
        // Using LinkedHashMap so the original order is preserved
        String startString = "Washington D.C=Redskins#New York City=Giants#Philadelphia=Eagles#Dallas=Cowboys";
        Map<String, String> testMap = Maps.newLinkedHashMap();
        testMap.put("Washington D.C", "Redskins");
        testMap.put("New York City", "Giants");
        testMap.put("Philadelphia", "Eagles");
        testMap.put("Dallas", "Cowboys");
        Splitter.MapSplitter mapSplitter = Splitter.on("#").withKeyValueSeparator("=");
        Map<String, String> splitMap = mapSplitter.split(startString);
        Assert.assertThat(testMap, Is.is(splitMap));
    }
}
