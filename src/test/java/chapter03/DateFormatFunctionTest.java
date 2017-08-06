package chapter03;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateFormatFunctionTest {

    @Test
    public void testFormatDate() {

        LocalDate localDate = LocalDate.of(2017, 8, 10);

        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        DateFormatFunction dff = new DateFormatFunction();

        String result = dff.apply(date);

        Assert.assertThat(result, Is.is("10/08/2017"));

    }
}
