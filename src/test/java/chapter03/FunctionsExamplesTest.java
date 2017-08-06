package chapter03;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by manoel on 8/5/17.
 */
public class FunctionsExamplesTest {

    private static Map<String, State> stateMap;

    @BeforeClass
    public static void setUp() {
        stateMap = new HashMap<>();

        State NY = new State("New York", "NY");
        NY.addCity(new City("New York", "zip1", 100, Climate.CONTINENTAL, 10));
        NY.addCity(new City("Albany", "zip2", 50, Climate.CONTINENTAL, 10));
        stateMap.put("NY", NY);

        State CA = new State("California", "CA");
        CA.addCity(new City("Mountain View", "zip3", 20, Climate.CONTINENTAL, 10));
        CA.addCity(new City("San Francisco", "zip4", 30, Climate.CONTINENTAL, 10));
        stateMap.put("CA", CA);
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

        String expected = "City{name=Albany, zipCode=zip2, population=50, climate=CONTINENTAL, averageRainfall=10.0}," +
                "City{name=New York, " +
                "zipCode=zip1," +
                " population=100, climate=CONTINENTAL, averageRainfall=10.0}";

        String result = composed.apply("NY");

        Assert.assertThat(result, Is.is(expected));
    }

}
