package chapter08;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ToListLineProcessorTest {

    @Test
    public void readLinesWithProcessor() throws IOException {
        File file = new File("src/test/resources/books.csv");

        List<String> expectedLines =
                Lists.newArrayList(
                        "Being A Great Cook",
                        "Art is Fun",
                        "Be an Architect",
                        "History of Football",
                        "Gardening My Way");

        List<String> readLines = Files.asCharSource(file, Charsets.UTF_8).readLines(new ToListLineProcessor());

        Assert.assertThat(readLines, Is.is(expectedLines));
    }

}
