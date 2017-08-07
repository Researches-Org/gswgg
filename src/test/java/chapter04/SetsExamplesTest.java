package chapter04;

import com.google.common.collect.Sets;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class SetsExamplesTest {

    @Test
    public void testSetsDifference() {

        Set<String> s1 = Sets.newHashSet("1", "2", "3");
        Set<String> s2 = Sets.newHashSet("2", "3", "4");

        Sets.SetView<String> setView = Sets.difference(s1, s2);

        Assert.assertThat(setView.size(), Is.is(1));
        Assert.assertThat(setView.contains("1"), Is.is(true));

    }

    @Test
    public void testSetsSymmetricDifference() {

        Set<String> s1 = Sets.newHashSet("1", "2", "3");
        Set<String> s2 = Sets.newHashSet("2", "3", "4");

        Sets.SetView<String> setView = Sets.symmetricDifference(s1, s2);

        Assert.assertThat(setView.size(), Is.is(2));
        Assert.assertThat(setView.contains("1"), Is.is(true));
        Assert.assertThat(setView.contains("4"), Is.is(true));

    }

    @Test
    public void testSetsIntersection() {
        Set<String> s1 = Sets.newHashSet("1", "2", "3");
        Set<String> s2 = Sets.newHashSet("2", "3", "4");

        Sets.SetView<String> setView = Sets.intersection(s1, s2);

        Assert.assertThat(setView.size(), Is.is(2));
        Assert.assertThat(setView.contains("2"), Is.is(true));
        Assert.assertThat(setView.contains("3"), Is.is(true));

    }

    @Test
    public void testSetsUnion() {
        Set<String> s1 = Sets.newHashSet("1", "2", "3");
        Set<String> s2 = Sets.newHashSet("2", "3", "4");

        Sets.SetView<String> setView = Sets.union(s1, s2);

        Assert.assertThat(setView.size(), Is.is(4));
        Assert.assertThat(setView.contains("1"), Is.is(true));
        Assert.assertThat(setView.contains("2"), Is.is(true));
        Assert.assertThat(setView.contains("3"), Is.is(true));
        Assert.assertThat(setView.contains("4"), Is.is(true));

    }
}
