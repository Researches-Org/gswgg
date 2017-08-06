package chapter03;

import com.google.common.base.Function;
import com.google.common.base.Joiner;

import javax.annotation.Nullable;

public class StateToCityString implements Function<State, String> {

    @Nullable
    @Override
    public String apply(@Nullable State state) {
        return Joiner.on(",").join(state.getMainCities());
    }
}
