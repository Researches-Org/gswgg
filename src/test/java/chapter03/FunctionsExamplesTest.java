package chapter03;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Maps;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class FunctionsExamplesTest {

    private static Map<String, State> stateMap;

    @BeforeClass
    public static void setUp() {
        stateMap = Maps.newLinkedHashMap();

        State NY = new State("New York", "NY", Region.MIDWEST);
        NY.addCity(new City("Albany", "zip2", 50, Climate.CONTINENTAL, 10));
        NY.addCity(new City("New York", "zip1", 100, Climate.CONTINENTAL, 10));
        stateMap.put("NY", NY);

        State CA = new State("California", "CA", Region.WEST);
        CA.addCity(new City("Mountain View", "zip3", 20, Climate.CONTINENTAL, 10));
        CA.addCity(new City("San Francisco", "zip4", 30, Climate.CONTINENTAL, 10));
        stateMap.put("CA", CA);
    }

    @Test
    public void testComposePredicate() {
        Function<String, State> lookup = Functions.forMap(stateMap);

        Predicate<String> p = Predicates.compose(new SouthwestOrMidwestRegionPredicate(), lookup);

        Assert.assertTrue(p.apply("NY"));
    }

    @Test
    public void testFunctionsForMap() {

        Function<String, State> lookup = Functions.forMap(stateMap);

        Assert.assertThat(lookup.apply("NY"), Is.is(stateMap.get("NY")));

    }

    @Test
    public void testFunctionsCompose() {
        Function<String, State> lookup = Functions.forMap(stateMap);

        Function<State, String> stateFunction = new StateToCityString();

        Function<String, String> composed = Functions.compose(stateFunction, lookup);

        String expected1 = "City{name=Albany, zipCode=zip2, population=50, climate=CONTINENTAL, " +
                "averageRainfall=10.0}," +
                "City{name=New York, " +
                "zipCode=zip1," +
                " population=100, climate=CONTINENTAL, averageRainfall=10.0}";

        String expected2 = "City{name=New York, zipCode=zip1, population=100, climate=CONTINENTAL, " +
                "averageRainfall=10.0},City{name=Albany, zipCode=zip2, population=50, climate=CONTINENTAL, averageRainfall=10.0}";

        String result = composed.apply("NY");

        Assert.assertThat(result.equals(expected1) || result.equals(expected2), Is.is(true));
    }

}
