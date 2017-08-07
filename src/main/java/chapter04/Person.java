package chapter04;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;

public class Person implements Comparable<Person> {

    private final String name;

    private final String surname;

    private final int age;

    private final String gender;

    public Person(String name, String surname, int age, String gender) {
        this.name = Preconditions.checkNotNull(name, "name can't be null");
        this.surname = Preconditions.checkNotNull(surname, "surname can't be null");
        Preconditions.checkArgument(age >= 0, "age can't be less than zero");
        this.age = age;
        this.gender = Preconditions.checkNotNull(gender, "gender can't be null");
        Preconditions.checkArgument(gender.equals("F") || gender.equals("M"),
                "gender can't be different from M or F");
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, surname, age, gender);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != getClass()) return false;
        Person other = (Person) obj;
        return Objects.equal(name, other.getName())
                && Objects.equal(surname, other.getSurname())
                && Objects.equal(age, other.getAge())
                && Objects.equal(gender, other.getGender());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("surname", surname)
                .add("age", age)
                .add("gender", gender)
                .toString();
    }

    @Override
    public int compareTo(Person o) {
        return ComparisonChain.start()
                .compare(name, o.getName())
                .compare(surname, o.getSurname())
                .compare(age, o.getAge())
                .compare(gender, o.getGender())
                .result();
    }
}
