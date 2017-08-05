package chapter02;

import com.google.common.base.Charsets;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Map;

public class CharsetsExamplesTest {

    @Test
    public void testGetBytesFromString() {

        String foobarbaz = "foobarbaz";

        Map<String, Charset> availableCharsets = Charset.availableCharsets();

        // six charsets must be present: US-ASCII, ISO-8859-1, UTF-8, UTF-16, UTF-16BE, UTF-16LE

        Assert.assertTrue(availableCharsets.containsKey("US-ASCII"));
        Assert.assertTrue(availableCharsets.containsKey("ISO-8859-1"));
        Assert.assertTrue(availableCharsets.containsKey("UTF-8"));
        Assert.assertTrue(availableCharsets.containsKey("UTF-16"));
        Assert.assertTrue(availableCharsets.containsKey("UTF-16BE"));
        Assert.assertTrue(availableCharsets.containsKey("UTF-16LE"));

        // without specifying the character set, the default will be used
        // and it can be changed from environment
        byte[] bytesWithDefaultCharset = foobarbaz.getBytes();

        Assert.assertThat(bytesWithDefaultCharset, Is.is(foobarbaz.getBytes(Charset.defaultCharset())));

        foobarbaz.getBytes(Charsets.UTF_8);
        foobarbaz.getBytes(Charsets.ISO_8859_1);
        foobarbaz.getBytes(Charsets.US_ASCII);
        foobarbaz.getBytes(Charsets.UTF_16);
        foobarbaz.getBytes(Charsets.UTF_16BE);
        foobarbaz.getBytes(Charsets.UTF_16LE);

        try {
            // Using a hard coded string as character set is not a good practise
            // a typo can cause an UnsupportedEncodingExceptin
            foobarbaz.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            // this really can' happen, UTF-8 must be supported
        }

        // The use of com.google.common.base.Charsets helps with this
        // Since Java 7, there is the class StandardCharsets

    }
}
