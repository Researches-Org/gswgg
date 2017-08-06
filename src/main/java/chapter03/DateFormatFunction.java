package chapter03;

import com.google.common.base.Function;

import javax.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatFunction implements Function<Date, String> {

    @Nullable
    @Override
    public String apply(@Nullable Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }
}
