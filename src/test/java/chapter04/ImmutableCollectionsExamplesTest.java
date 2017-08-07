package chapter04;

import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class ImmutableCollectionsExamplesTest {

    @Test
    public void testImmutableListMultiMapBuilder() {

        Multimap<Integer, String> map = new ImmutableListMultimap.Builder<Integer, String>()
                .put(1, "Foo")
                .putAll(2, "Foo", "Bar", "Baz")
                .putAll(4, "Huey", "Duey", "Luey")
                .put(3, "Single")
                .build();

        Assert.assertThat(map.get(1), Is.is(Lists.newArrayList("Foo")));
        Assert.assertThat(map.get(2), Is.is(Lists.newArrayList("Foo", "Bar", "Baz")));
        Assert.assertThat(map.get(4), Is.is(Lists.newArrayList("Huey", "Duey", "Luey")));
        Assert.assertThat(map.get(3), Is.is(Lists.newArrayList("Single")));

    }

}
