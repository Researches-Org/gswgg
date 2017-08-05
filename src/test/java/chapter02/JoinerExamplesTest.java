package chapter02;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import org.hamcrest.core.Is;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import util.FileUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JoinerExamplesTest {

    private static final String fileName = "path";

    @AfterClass
    public static void tearDown() {
        FileUtil.deleteFile(fileName);
    }

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

    @Test
    public void testBuildStringWithJoinerWithUseForNullsPresent() {
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add(null);
        stringList.add("b");

        String expected = "a|no value|b";

        Assert.assertThat(JoinerExamples.buildStringWithJoiner(stringList, "|", "no value"), Is.is(expected));
    }

    @Test
    public void testStringJoiner() {
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add(null);
        stringList.add("b");

        String expected = "a|b";

        Assert.assertThat(JoinerExamples.stringJoiner.join(stringList), Is.is(expected));
    }

    @Test
    public void testAppendToStringBuilder() {
        Assert.assertThat(JoinerExamples.appendToStringBuilder("foo", "bar", "baz").toString(), Is.is("foo|bar|baz"));
    }

    @Test
    public void testAppendToFileWriter() throws IOException {
        List<LocalDate> dateList = new ArrayList<>();
        LocalDate date1 = LocalDate.of(2017, 8, 5);
        LocalDate date2 = LocalDate.of(2017, 8, 17);
        dateList.add(date1);
        dateList.add(date2);

        FileWriter fileWriter = JoinerExamples.appendToFileWriter(fileName, dateList);
        fileWriter.close();

        String content = FileUtil.readFile(fileName);

        Assert.assertThat(content, Is.is(date1 + "#" + date2));
    }

    @Test
    public void testMapJoiner() {
        // Using LinkedHashMap so the original order is preserved
        String expectedString = "Washington D.C=Redskins#New York City=Giants#Philadelphia=Eagles#Dallas=Cowboys";
        Map<String, String> testMap = Maps.newLinkedHashMap();
        testMap.put("Washington D.C", "Redskins");
        testMap.put("New York City", "Giants");
        testMap.put("Philadelphia", "Eagles");
        testMap.put("Dallas", "Cowboys");
        String returnedString = Joiner.on("#")
                .withKeyValueSeparator("=")
                .join(testMap);
        Assert.assertThat(returnedString, Is.is(expectedString));
    }

}
