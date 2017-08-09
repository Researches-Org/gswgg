package chapter09;

import chapter02.Book;
import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

public enum BookFunnel implements Funnel<Book> {

    FUNNEL;

    @Override
    public void funnel(Book book, PrimitiveSink primitiveSink) {
        primitiveSink
                .putBytes(book.getIsbn().getBytes(Charsets.UTF_8))
                .putDouble(book.getPrice());
    }
}
