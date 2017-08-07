package chapter04;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class MultimapExamplesTest {

    @Test
    public void testArrayListMultiMap() {
        ArrayListMultimap<String, String> multimap = ArrayListMultimap.create();

        multimap.put("Foo", "1");
        multimap.put("Foo", "2");
        multimap.put("Foo", "3");

        List<String> expected = Lists.newArrayList("1", "2", "3");

        Assert.assertThat(multimap.get("Foo"), Is.is(expected));

    }

    @Test
    public void testArrayListMultiMapSameKeyValue() {
        ArrayListMultimap<String, String> multimap = ArrayListMultimap.create();

        multimap.put("Bar", "1");
        multimap.put("Bar", "2");
        multimap.put("Bar", "3");
        multimap.put("Bar", "3");
        multimap.put("Bar", "3");

        List<String> expected = Lists.newArrayList("1", "2", "3", "3", "3");

        Assert.assertThat(multimap.get("Bar"), Is.is(expected));

    }

    @Test
    public void testHashMultimap() {
        HashMultimap<String, String> multimap = HashMultimap.create();
        multimap.put("Bar", "1");
        multimap.put("Bar", "2");
        multimap.put("Bar", "3");
        multimap.put("Bar", "3");
        multimap.put("Bar", "3");

        Set<String> expected = Sets.newHashSet("1", "2", "3");

        Assert.assertThat(multimap.size(), Is.is(3));
        Assert.assertThat(multimap.get("Bar"), Is.is(expected));
    }

}
