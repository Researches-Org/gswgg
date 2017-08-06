package chapter03;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Test;

public class StateTest {

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullName() {
        new State(null, "NY");
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullCode() {
        new State("New York", null);
    }

    @Test
    public void testToString() {
        State state = new State("New York", "NY");
        String expected = "State{name=New York, code=NY}";
        Assert.assertThat(state.toString(), Is.is(expected));
    }

    @Test
    public void testEqualsAndHashCode() {
        State state1 = new State("New York", "NY");
        State state2 = new State("New York", "NY");
        State state3 = new State("State3", "ST");

        Assert.assertThat(state1, Is.is(state2));
        Assert.assertThat(state1.hashCode(), Is.is(state2.hashCode()));
        Assert.assertThat(state1, IsNot.not(state3));
        Assert.assertThat(state2, IsNot.not(state3));
    }

}
