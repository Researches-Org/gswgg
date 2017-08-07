package chapter04;

import chapter03.City;
import com.google.common.primitives.Doubles;

import java.util.Comparator;

public class CityByRainfall implements Comparator<City> {

    @Override
    public int compare(City city1, City city2) {
        return Doubles.compare(city1.getAverageRainfall(),
                city2.getAverageRainfall());
    }
}