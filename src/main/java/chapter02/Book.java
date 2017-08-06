package chapter02;

import static com.google.common.base.Preconditions.*;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

public final class Book implements Comparable<Book> {

    private String author;
    private String title;
    private String publisher;
    private String isbn;
    private double price;

    public Book(String author, String title, String publisher, String isbn, double price) {
        this.author = checkNotNull(author, "author can't be null");
        this.title = checkNotNull(title, "title can't be null");
        this.publisher = checkNotNull(publisher, "publisher can' be null");
        this.isbn = checkNotNull(isbn, "isbn can' be null");
        checkArgument(price > 0, "price can' be less or equal to zero");
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public double getPrice() {
        return price;
    }

    public String getPublisherOrDefault() {
        return MoreObjects.firstNonNull(publisher, "Default Publisher");
    }

    @Override
    public int compareTo(Book other) {
        return ComparisonChain.start()
                .compare(title, other.getTitle())
                .compare(author, other.getAuthor())
                .compare(publisher, other.getPublisher())
                .compare(isbn, other.getIsbn())
                .compare(price, other.getPrice())
                .result();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title, author, publisher, isbn);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        // I would like to point out that checking exact equality of classes in equals should be used carefully.
        // If the class is final, then there is no problem. However, if sub classes may be considered equal with
        // others, then the equals will always fail.
        if (obj.getClass() != getClass()) { return false; }
        Book other = (Book) obj;
        return Objects.equal(this.title, other.title)
                && Objects.equal(this.author, other.author)
                && Objects.equal(this.publisher, other.publisher)
                && Objects.equal(this.isbn, other.isbn);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("title", title)
                .add("author", author)
                .add("publisher", publisher)
                .add("price", price)
                .add("isbn", isbn).toString();
    }

}
