package chapter04;

import chapter02.Book;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

public class MapsExamplesTest {

    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;

    private List<Book> bookList;

    @Before
    public void setUp() {
        book1 = new Book("a1", "t1", "p1", "i1", 10);
        book2 = new Book("a2", "t2", "p2", "i2", 20);
        book3 = new Book("a3", "t3", "p3", "i3", 30);
        book4 = new Book("a4", "t4", "p4", "i4", 40);

        bookList = Lists.newArrayList(book1, book2, book3, book4);
    }

    private Function<Book, String> createBookMapFn() {
        return new Function<Book, String>() {
            @Nullable
            @Override
            public String apply(@Nullable Book book) {
                return book.getIsbn();
            }
        };
    }

    @Test
    public void testMapsUniqueIndex() {

        Map<String, Book> bookMap = Maps.uniqueIndex(bookList.iterator(), createBookMapFn());

        Assert.assertThat(bookMap.size(), Is.is(4));
        Assert.assertThat(bookMap.get("i1"), Is.is(book1));
        Assert.assertThat(bookMap.get("i2"), Is.is(book2));
        Assert.assertThat(bookMap.get("i3"), Is.is(book3));
        Assert.assertThat(bookMap.get("i4"), Is.is(book4));
    }

    @Test
    public void testMapsAsMap() {
        Set<Book> bookSet = Sets.newHashSet(bookList);
        Map<Book, String> bookMap = Maps.asMap(bookSet, new Function<Book, String>() {
            @Nullable
            @Override
            public String apply(@Nullable Book book) {
                return book.getIsbn();
            }
        });

        // Modifications to the backing set are read through to the returned map.
        Book book5 = new Book("a5", "t5", "p5", "i5", 50);
        bookSet.add(book5);

        Assert.assertThat(bookMap.size(), Is.is(5));
        Assert.assertThat(bookMap.get(book1), Is.is("i1"));
        Assert.assertThat(bookMap.get(book2), Is.is("i2"));
        Assert.assertThat(bookMap.get(book3), Is.is("i3"));
        Assert.assertThat(bookMap.get(book4), Is.is("i4"));
        Assert.assertThat(bookMap.get(book5), Is.is("i5"));

    }

    @Test
    public void testMapsToMap() {
        Map<Book, String> bookMap = Maps.toMap(Sets.newHashSet(bookList), new Function<Book, String>() {
            @Nullable
            @Override
            public String apply(@Nullable Book book) {
                return book.getIsbn();
            }
        });

        Assert.assertThat(bookMap.size(), Is.is(4));
        Assert.assertThat(bookMap.get(book1), Is.is("i1"));
        Assert.assertThat(bookMap.get(book2), Is.is("i2"));
        Assert.assertThat(bookMap.get(book3), Is.is("i3"));
        Assert.assertThat(bookMap.get(book4), Is.is("i4"));

    }

    @Test
    public void testMapsTransformEntries() {

        Map<String, Book> bookMap = Maps.uniqueIndex(bookList.iterator(), createBookMapFn());

        Map<String, String> isbnToAuthorMap = Maps.transformEntries(bookMap, new Maps.EntryTransformer<String, Book,
                String>() {
            @Override
            public String transformEntry(@Nullable String isbn, @Nullable Book book) {
                return book.getAuthor();
            }
        });

        Assert.assertThat(isbnToAuthorMap.size(), Is.is(4));
        Assert.assertThat(isbnToAuthorMap.get("i1"), Is.is("a1"));
        Assert.assertThat(isbnToAuthorMap.get("i2"), Is.is("a2"));
        Assert.assertThat(isbnToAuthorMap.get("i3"), Is.is("a3"));
        Assert.assertThat(isbnToAuthorMap.get("i4"), Is.is("a4"));
    }

    @Test
    public void testMapsTransformValues() {

        Map<String, Book> bookMap = Maps.uniqueIndex(bookList.iterator(), createBookMapFn());

        Map<String, String> isbnToAuthorMap = Maps.transformValues(bookMap, new Function<Book, String>() {
            @Nullable
            @Override
            public String apply(@Nullable Book book) {
                return book.getAuthor();
            }
        });

        Assert.assertThat(isbnToAuthorMap.size(), Is.is(4));
        Assert.assertThat(isbnToAuthorMap.get("i1"), Is.is("a1"));
        Assert.assertThat(isbnToAuthorMap.get("i2"), Is.is("a2"));
        Assert.assertThat(isbnToAuthorMap.get("i3"), Is.is("a3"));
        Assert.assertThat(isbnToAuthorMap.get("i4"), Is.is("a4"));
    }

}
