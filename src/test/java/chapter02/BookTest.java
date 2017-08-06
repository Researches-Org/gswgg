package chapter02;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Test;

public class BookTest {

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullTitle() {
        new Book("author", null, "publisher", "isbn", 10);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullAuthor() {
        new Book(null, "title", "publisher", "isbn", 10);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullPublisher() {
        new Book("author", "title", null, "isbn", 10);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullIsbn() {
        new Book("author", "title", "publisher", null, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidPrice() {
        new Book("author", "title", "publisher", "isbn", 0);
    }

    @Test
    public void testToString() {
        Book book = new Book("author", "title", "publisher", "isbn", 10);
        String actual = book.toString();
        String expected = "Book{title=title, author=author, publisher=publisher, price=10.0, isbn=isbn}";
        Assert.assertThat(actual, Is.is(expected));
    }

    @Test
    public void testEqualsAndHashCode() {
        Book book1 = new Book("author", "title", "publisher", "isbn", 10);
        Book book2 = new Book("author", "title", "publisher", "isbn", 10);
        Book book3 = new Book("author3", "title3", "publisher3", "isbn3", 10);

        Assert.assertThat(book1, Is.is(book2));
        Assert.assertThat(book1.hashCode(), Is.is(book2.hashCode()));
        Assert.assertThat(book1, IsNot.not(book3));
        Assert.assertThat(book2, IsNot.not(book3));

    }

    @Test
    public void testCompareTo() {
        Book book1 = new Book("author", "title", "publisher", "isbn", 10);
        Book book2 = new Book("author", "title", "publisher", "isbn", 10);
        Book book3 = new Book("author3", "title3", "publisher3", "isbn3", 10);

        Assert.assertThat(book1.compareTo(book2), Is.is(0));
        Assert.assertThat(book2.compareTo(book1), Is.is(0));
        Assert.assertThat(book1.compareTo(book3), Is.is(-1));
        Assert.assertThat(book2.compareTo(book3), Is.is(-1));
        Assert.assertThat(book3.compareTo(book1), Is.is(1));
    }

}
