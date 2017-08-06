package chapter03;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import static com.google.common.base.Preconditions.*;

public final class City {

    private final String name;

    private final String zipCode;

    private final int population;

    public City(String name, String zipCode, int population) {
        this.name = checkNotNull(name, "name can't be null");
        this.zipCode = checkNotNull(zipCode, "zipCode can' be null");
        checkArgument(population > 0, "population can't be equal or less than zero");
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public String getZipCode() {
        return zipCode;
    }

    public int getPopulation() {
        return population;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, zipCode, population);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != getClass()) return false;
        City other = (City) obj;
        return Objects.equal(name, other.getName())
                && Objects.equal(zipCode, other.getZipCode())
                && Objects.equal(population, other.getPopulation());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("zipCode", zipCode)
                .add("population", population)
                .toString();
    }
}
