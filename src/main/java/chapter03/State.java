package chapter03;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import com.sun.org.apache.regexp.internal.RE;

import static com.google.common.base.Preconditions.*;

import java.util.HashSet;
import java.util.Set;

public final class State {

    private final String name;

    private final String code;

    private final Region region;

    private Set<City> mainCities = Sets.newLinkedHashSet();

    public State(String name, String code, Region region) {
        this.name = checkNotNull(name, "name can't be null");
        this.code = checkNotNull(code, "code can't be null");
        this.region = checkNotNull(region, "region can't be null");
    }

    public State(String name, String code, Region region, Set<City> mainCities) {
        this(name, code, region);
        checkNotNull(mainCities, "main cities can't be null");
        this.mainCities = Sets.newLinkedHashSet();
    }

    public Set<City> getMainCities() {
        return new HashSet<>(mainCities);
    }

    public void addCity(City city) {
        checkNotNull(city, "city can' be null");
        mainCities.add(city);
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Region getRegion() {
        return region;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, code, region);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != getClass()) return false;
        State other = (State) obj;
        return Objects.equal(name, other.getName())
                && Objects.equal(code, other.getCode())
                && Objects.equal(region, other.getRegion());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("code", code)
                .add("region", region)
                .toString();
    }
}
