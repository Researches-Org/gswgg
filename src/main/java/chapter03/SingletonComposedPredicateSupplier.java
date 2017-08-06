package chapter03;

import com.google.common.base.Predicate;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

import java.util.concurrent.TimeUnit;

public final class SingletonComposedPredicateSupplier implements Supplier<Predicate<String>> {

    private final Supplier<Predicate<String>> supplier = Suppliers.memoize(new ComposedPredicateSupplier());

    // If it is returning an object that could have changes, something retrieved from a
    // database, for example, the memoizeWithExpiration method, could be very helpful.
    // private final Supplier<Predicate<String>> supplier = Suppliers.memoizeWithExpiration(new
    // ComposedPredicateSupplier(), 10L, TimeUnit.MINUTES);

    @Override
    public Predicate<String> get() {
        return supplier.get();
    }
}
