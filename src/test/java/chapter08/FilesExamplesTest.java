package chapter08;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.*;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.List;
import java.util.regex.Pattern;

public class FilesExamplesTest {

    @Test
    public void readFileIntoListOfStrings() throws IOException {
        File file = new File("src/test/resources/lines.txt");

        List<String> expectedLines = Lists.newArrayList("The quick brown", "fox jumps over", "the lazy dog");

        List<String> readLines = Files.readLines(file, Charsets.UTF_8);

        Assert.assertThat(readLines, Is.is(expectedLines));
    }

    @Test
    public void appendingWritingToFile() throws IOException {
        File file = new File("src/test/resources/quote.txt");
        file.deleteOnExit();

        String hamletQuoteStart = "To be, or not to be";
        Files.asCharSink(file, Charsets.UTF_8).write(hamletQuoteStart);
        Assert.assertThat(Files.asCharSource(file, Charsets.UTF_8).read(), Is.is(hamletQuoteStart));

        String hamletQuoteEnd = ",that is the question";
        Files.asCharSink(file, Charsets.UTF_8, FileWriteMode.APPEND).write(hamletQuoteEnd);
        Assert.assertThat(Files.asCharSource(file, Charsets.UTF_8).read(), Is.is(hamletQuoteStart + hamletQuoteEnd));

        String overwrite = "overwriting the file";
        Files.asCharSink(file, Charsets.UTF_8).write(overwrite);
        Assert.assertThat(Files.asCharSource(file, Charsets.UTF_8).read(), Is.is(overwrite));

    }

    @Test
    public void createByteSourceFromFile() throws IOException {
        File file = new File("src/test/resources/sample.pdf");
        ByteSource byteSource = Files.asByteSource(file);
        byte[] readBytes = byteSource.read();
        Assert.assertThat(readBytes, Is.is(Files.toByteArray(file)));
    }

    @Test
    public void createFileByteSink() throws IOException {
        File dest = new File("src/test/resources/byteSync.pdf");
        dest.deleteOnExit();
        ByteSink byteSink = Files.asByteSink(dest);
        File file = new File("src/test/resources/sample.pdf");
        byteSink.write(Files.toByteArray(file));
        Assert.assertThat(Files.toByteArray(dest), Is.is(Files.toByteArray(file)));
    }

    @Test
    public void copyToByteSinc() throws IOException {
        File dest = new File("src/test/resources/sampleCompany.pdf");
        dest.deleteOnExit();
        File source = new File("src/test/resources/sample.pdf");
        ByteSource byteSource = Files.asByteSource(source);
        ByteSink byteSink = Files.asByteSink(dest);
        byteSource.copyTo(byteSink);
        Assert.assertThat(Files.toByteArray(dest), Is.is(Files.toByteArray(source)));
    }

    @Test
    public void limitByteStream() throws IOException {
        File binaryFile = new File("src/test/resources/sample.pdf");
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(binaryFile))) {
            InputStream limitedInputStream = ByteStreams.limit(inputStream, 10);
            Assert.assertThat(limitedInputStream.available(), Is.is(10));
            Assert.assertThat(inputStream.available() > 10, Is.is(true));
        }
    }

    @Test
    public void concat() throws IOException {
        File f1 = new File("src/test/resources/sampleTextFileOne.txt");
        File f2 = new File("src/test/resources/sampleTextFileTwo.txt");
        File f3 = new File("src/test/resources/lines.txt");

        File concatOutput = new File("src/test/resources/concat.txt");
        concatOutput.deleteOnExit();

        List<CharSource> charSources = getCharSources(f1, f2, f3);
        CharSource concatCharSource = CharSource.concat(charSources);
        CharSink charSink = Files.asCharSink(concatOutput, Charsets.UTF_8);
        concatCharSource.copyTo(charSink);

        String expectedOutputString = concatFiles(f1, f2, f3);
        String concatOutputString = concatFiles(concatOutput);

        Assert.assertThat(concatOutputString, Is.is(expectedOutputString));

    }

    @Test
    public void closer()throws IOException {
        Closer closer = Closer.create();
        try {
            File destination = new File("src/test/resources/copy.txt");
            destination.deleteOnExit();

            File source = new File("src/test/resources/sampleTextFileOne.txt");

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(source));

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(destination));

            closer.register(reader);
            closer.register(writer);

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
            }
            writer.flush();

            Assert.assertThat(
                    Files.asCharSource(destination, Charsets.UTF_8).read(),
                    Is.is(Files.asCharSource(source, Charsets.UTF_8).read().replace(System.lineSeparator(), "")));

        } catch (Throwable t) {
            throw closer.rethrow(t);
        } finally {
            closer.close();
        }
    }

    @Test
    public void encodeDecode() throws IOException {
        File file = new File("src/test/resources/sample.pdf");

        byte[] bytes = Files.toByteArray(file);

        BaseEncoding baseEncoding = BaseEncoding.base64();

        String encoded = baseEncoding.encode(bytes);

        Assert.assertThat(Pattern.matches("[A-Za-z0-9+/=]+", encoded), Is.is(true));
        Assert.assertThat(baseEncoding.decode(encoded), Is.is(bytes));
    }

    @Test
    public void encodeByteSink() throws IOException {
        File file = new File("src/test/resources/sample.pdf");

        File encodedFile = new File("src/test/resources/encoded.txt");
        encodedFile.deleteOnExit();

        CharSink charSink = Files.asCharSink(encodedFile, Charsets.UTF_8);

        BaseEncoding baseEncoding = BaseEncoding.base64();

        ByteSink byteSink = baseEncoding.encodingSink(charSink);

        ByteSource byteSource = Files.asByteSource(file);

        byteSource.copyTo(byteSink);

        String encodedBytes = baseEncoding.encode(byteSource.read());

        Assert.assertThat(encodedBytes, Is.is(Files.asCharSource(encodedFile, Charsets.UTF_8).read()));

    }

    private String concatFiles(File... files) throws IOException {
        StringBuilder builder = new StringBuilder();
        for (File file: files) {
            builder.append(Files.asCharSource(file, Charsets.UTF_8).read());
        }
        return builder.toString();
    }

    private List<CharSource> getCharSources(File... files) {
        List<CharSource> result = Lists.newArrayList();
        for (File file : files) {
            result.add(Files.asCharSource(file, Charsets.UTF_8));
        }
        return result;
    }

}
