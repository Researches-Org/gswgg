package chapter03;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Test;

public class CityTest {

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullName() {
        new City(null, "zip", 100);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullZip() {
        new City("city", null, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidPopulation() {
        new City("city", "zip", 0);
    }

    @Test
    public void testToString() {
        City city = new City("city", "zip", 100);

        String expected = "City{name=city, zipCode=zip, population=100}";

        Assert.assertThat(city.toString(), Is.is(expected));
    }

    @Test
    public void testEqualsAndHashCode() {
        City city1 = new City("city", "zip", 100);
        City city2 = new City("city", "zip", 100);
        City city3 = new City("city3", "zip3", 100);

        Assert.assertThat(city1, Is.is(city2));
        Assert.assertThat(city1.hashCode(), Is.is(city2.hashCode()));
        Assert.assertThat(city1, IsNot.not(city3));
        Assert.assertThat(city2, IsNot.not(city3));
    }

}
