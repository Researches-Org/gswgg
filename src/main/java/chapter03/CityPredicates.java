package chapter03;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class CityPredicates {

    private static final Predicate<City> SMALL = new PopulationPredicate();

    private static final Predicate<City> DRY = new LowRainFallPredicate();

    private static final Predicate<City> TEMPERATE = new TemperateClimatePredicate();

    private static final Predicate<City> SMALL_AND_DRY = Predicates.and(SMALL, DRY);

    private static final Predicate<City> SMALL_OR_TEMPERATE = Predicates.or(SMALL, TEMPERATE);

    private static final Predicate<City> LARGE = Predicates.not(SMALL);

    public static Predicate<City> small() {
        return SMALL;
    }

    public static Predicate<City> dry() {
        return DRY;
    }

    public static Predicate<City> temperate() {
        return TEMPERATE;
    }

    public static Predicate<City> smallAndDry() {
        return SMALL_AND_DRY;
    }

    public static Predicate<City> smallOrTemperate() {
        return  SMALL_OR_TEMPERATE;
    }

    public static Predicate<City> large() {
        return LARGE;
    }
}
