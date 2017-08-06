package chapter03;

import com.google.common.base.Predicate;

import javax.annotation.Nullable;

public class TemperateClimatePredicate implements Predicate<City> {

    @Override
    public boolean apply(@Nullable City city) {
        return city.getClimate().equals(Climate.TEMPERATE);
    }
}
