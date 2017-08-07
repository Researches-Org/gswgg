package chapter04;

import chapter03.City;
import com.google.common.primitives.Ints;

import java.util.Comparator;

public class CityByPopulation implements Comparator<City> {

    @Override
    public int compare(City o1, City o2) {
        return Ints.compare(o1.getPopulation(), o2.getPopulation());
    }
}
