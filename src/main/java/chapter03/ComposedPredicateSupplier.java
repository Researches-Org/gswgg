package chapter03;

import com.google.common.base.*;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Map;

public class ComposedPredicateSupplier implements Supplier<Predicate<String>> {

    @Override
    public Predicate<String> get() {

        City austin = new City("Austin,TX", "12345", 250000, Climate.SUB_TROPICAL, 45.3);
        State texas = new State("Texas", "TX", Region.SOUTHWEST, Sets.newHashSet(austin));

        City newYork = new City("New York,NY", "12345", 2000000, Climate.TEMPERATE, 48.7);
        State ny = new State("New York", "NY", Region.NORTHEAST, Sets.newHashSet(newYork));

        Map<String, State> stateMap = Maps.newHashMap();
        stateMap.put(texas.getCode(), texas);
        stateMap.put(ny.getCode(), ny);

        Function<String, State> mf = Functions.forMap(stateMap);

        return Predicates.compose(new RegionPredicate(), mf);

    }
}
