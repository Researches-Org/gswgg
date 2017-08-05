package chapter02;

import com.google.common.base.Joiner;

import java.util.List;

public class JoinerExamples {

    public static final Joiner stringJoiner = Joiner.on("|").skipNulls();

    public static String buildString(List<String> stringList, String delimiter) {
        StringBuilder builder = new StringBuilder();
        for (String s: stringList) {
            if (s != null) {
                builder.append(s).append(delimiter);
            }
        }
        builder.setLength(builder.length() - delimiter.length());
        return builder.toString();
    }

    public static String buildStringWithJoiner(List<String> stringList, String delimiter) {
        return Joiner.on(delimiter).skipNulls().join(stringList);
    }

    public static String buildStringWithJoiner(List<String> stringList, String delimiter, String userForNull) {
        return Joiner.on(delimiter).useForNull(userForNull).join(stringList);
    }
}
