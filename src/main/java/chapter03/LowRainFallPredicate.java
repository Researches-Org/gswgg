package chapter03;

import com.google.common.base.Predicate;

import javax.annotation.Nullable;

public class LowRainFallPredicate implements Predicate<City> {

    @Override
    public boolean apply(@Nullable City city) {
        return city.getAverageRainfall() < 45.7;
    }
}
