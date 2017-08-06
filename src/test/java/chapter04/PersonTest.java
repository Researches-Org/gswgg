package chapter04;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.List;

public class PersonTest {

    private Person person1;
    private Person person2;
    private Person person3;
    private Person person4;

    private List<Person> personList;

    @Before
    public void setUp() {
        person1 = new Person("Wilma", "Flintstone", 30, "F");
        person2 = new Person("Fred", "Flintstone", 32, "M");
        person3 = new Person("Betty", "Rubble", 31, "F");
        person4 = new Person("Barney", "Rubble", 33, "M");
        personList = Lists.newArrayList(person1, person2, person3, person4);
    }

    @Test
    public void testFilter() {

        Iterable<Person> personsFilteredByAge =
                FluentIterable
                        .from(personList)
                        .filter(
                                new Predicate<Person>() {
                                    @Override
                                    public boolean apply(@Nullable Person person) {
                                        return person.getAge() > 31;
                                    }
                                });

        Assert.assertThat(Iterables.contains(personsFilteredByAge, person1), Is.is(false));
        Assert.assertThat(Iterables.contains(personsFilteredByAge, person2), Is.is(true));
        Assert.assertThat(Iterables.contains(personsFilteredByAge, person3), Is.is(false));
        Assert.assertThat(Iterables.contains(personsFilteredByAge, person4), Is.is(true));

    }

}