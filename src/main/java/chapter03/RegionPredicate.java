package chapter03;

import com.google.common.base.Predicate;

import javax.annotation.Nullable;

public class RegionPredicate implements Predicate<State> {

    @Override
    public boolean apply(@Nullable State state) {
        return state.getRegion().equals(Region.MIDWEST) || state.getRegion().equals(Region.SOUTHWEST);
    }
}
