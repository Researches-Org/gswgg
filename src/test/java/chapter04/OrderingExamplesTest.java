package chapter04;

import chapter03.City;
import chapter03.CityBuilder;
import chapter03.Climate;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class OrderingExamplesTest {

    private City city1;
    private City city2;
    private City city3;

    private List<City> cities;

    @Before
    public void setUp() {
        city1 = new City("city", "zip", 100, Climate.CONTINENTAL, 35);
        city2 = new City("city", "zip", 200, Climate.CONTINENTAL, 45);
        city3 = new City("city3", "zip3", 300, Climate.CONTINENTAL, 33.8);

        cities = Lists.newArrayList(city1, city2, city3);
    }

    @Test
    public void testReverseOrdering() {

        Ordering<City> cityOrdering = Ordering.from(new CityByPopulation()).reverse();

        cities.sort(cityOrdering);

        Assert.assertThat(cities.get(0), Is.is(city3));
        Assert.assertThat(cities.get(1), Is.is(city2));
        Assert.assertThat(cities.get(2), Is.is(city1));

    }

    @Test
    public void testSecondarySort(){
        CityBuilder cityBuilder = new CityBuilder();
        City city1 = cityBuilder
                .name("city1")
                .zipCode("zip1")
                .climate(Climate.CONTINENTAL)
                .population(100000)
                .averageRainfall(55.0)
                .build();
        City city2 = cityBuilder
                .name("city2")
                .zipCode("zip2")
                .climate(Climate.CONTINENTAL)
                .population(100000)
                .averageRainfall(45.0)
                .build();
        City city3 = cityBuilder
                .name("city3")
                .zipCode("zip3")
                .climate(Climate.CONTINENTAL)
                .population(100000)
                .averageRainfall(33.8)
                .build();

        List<City> cities = Lists.newArrayList(city1,city2,city3);


        Ordering<City> secondaryOrdering = Ordering.
                from(new CityByPopulation()).compound(new CityByRainfall());

        cities.sort(secondaryOrdering);

        Assert.assertThat(cities.get(0), Is.is(city3));
    }
}
