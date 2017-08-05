package chapter02;


import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class PreconditionExampleTest {

    @Test(expected = NullPointerException.class)
    public void testWithNullLabel() {
        new PreconditionExample(null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testWithInvalidIndex() {
        PreconditionExample pe = new PreconditionExample("open");
        pe.updateCurrentIndexValue(-1, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithInvalidValue() {
        PreconditionExample pe = new PreconditionExample("open");
        pe.updateCurrentIndexValue(0, 101);
    }

    @Test(expected = IllegalStateException.class)
    public void testWithInvalidLabel() {
        PreconditionExample pe = new PreconditionExample("label");
        pe.doOperation();
    }

    @Test(expected = IllegalStateException.class)
    public void testWithInvalidValueAtCurrentIndex() {
        PreconditionExample pe = new PreconditionExample("open");
        pe.doOperation();
    }

    @Test
    public void testWithValidState() {
        PreconditionExample pe = new PreconditionExample("open");
        pe.updateCurrentIndexValue(0, 10);
        pe.doOperation();
        Assert.assertThat(pe.getValue(0), Is.is(10));
    }

}
