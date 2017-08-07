package chapter04;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Range;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Nullable;

public class RangeExamplesTest {

    private Person person1;
    private Person person2;
    private Person person3;
    private Person person4;

    @Before
    public void setUp() {
        person1 = new Person("Batman", "Batman", 37, "M");
        person2 = new Person("Spider man", "Spider man", 33, "M");
        person3 = new Person("Iron man", "Iron man", 39, "M");
        person4 = new Person("Wonder Woman", "Wonder Woman", 33, "F");
    }

    @Test
    public void testRangeAsPredicate() {

        Range<Integer> ageRange = Range.closed(35, 50);

        Function<Person, Integer> ageFunction = new Function<Person, Integer>() {
            @Nullable
            @Override
            public Integer apply(@Nullable Person person) {
                return person.getAge();
            }
        };


        Predicate<Person> personPredicate = Predicates.compose(ageRange, ageFunction);

        Assert.assertThat(personPredicate.apply(person1), Is.is(true));
        Assert.assertThat(personPredicate.apply(person2), Is.is(false));
        Assert.assertThat(personPredicate.apply(person3), Is.is(true));
        Assert.assertThat(personPredicate.apply(person4), Is.is(false));

    }

}
