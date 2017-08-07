package chapter04;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class BiMapExamplesTest {

    @Test(expected = IllegalArgumentException.class)
    public void testInsertSameValueTwice() {

        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("1", "Tom");
        biMap.put("2", "Tom");

    }

    @Test
    public void testBiMapForcePut() {
        BiMap<String, String> biMap = HashBiMap.create();

        biMap.put("1", "Tom");
        biMap.forcePut("2", "Tom");

        Assert.assertThat(biMap.containsKey("1"), Is.is(false));
        Assert.assertThat(biMap.containsKey("2"), Is.is(true));
    }

    @Test
    public void testBiMapInverse() {
        BiMap<String, String> biMap = HashBiMap.create();

        biMap.put("1", "Tom");
        biMap.put("2", "Harry");

        Assert.assertThat(biMap.get("1"), Is.is("Tom"));
        Assert.assertThat(biMap.get("2"), Is.is("Harry"));

        BiMap<String, String> inverseMap = biMap.inverse();

        Assert.assertThat(inverseMap.get("Tom"), Is.is("1"));
        Assert.assertThat(inverseMap.get("Harry"), Is.is("2"));
    }
}
