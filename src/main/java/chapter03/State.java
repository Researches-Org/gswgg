package chapter03;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import static com.google.common.base.Preconditions.*;

import java.util.HashSet;
import java.util.Set;

public final class State {

    private final String name;

    private final String code;

    private Set<City> mainCities = new HashSet<>();

    public State(String name, String code) {
        this.name = checkNotNull(name, "name can' be null");
        this.code = checkNotNull(code, "code can' be null");
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

    @Override
    public int hashCode() {
        return Objects.hashCode(name, code);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != getClass()) return false;
        State other = (State) obj;
        return Objects.equal(name, other.getName())
                && Objects.equal(code, other.getCode());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("code", code)
                .toString();
    }
}
