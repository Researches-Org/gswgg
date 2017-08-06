package chapter03;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CityPredicatesTest {

    private static List<City> cities;

    @BeforeClass
    public static void setUp() {
        cities = new ArrayList<>();
        cities.add(new City("New York", "zip1", 600000, Climate.CONTINENTAL, 48));
        cities.add(new City("Rio de Janeiro", "zip2", 800000, Climate.TEMPERATE, 49));
        cities.add(new City("Vancouver", "zip3", 700000, Climate.POLAR, 60));
    }

    @Test
    public void testSmallCityPredicate() {

        List<City> smallCities = cities.stream().filter(CityPredicates.small()).collect(Collectors.toList());

        Assert.assertThat(smallCities.size(), Is.is(0));

    }

    @Test
    public void testDryCityPredicate() {

        List<City> dryCities = cities.stream().filter(CityPredicates.dry()).collect(Collectors.toList());

        Assert.assertThat(dryCities.size(), Is.is(0));

    }
}
