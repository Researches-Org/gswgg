package chapter09;

import chapter02.Book;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.hash.BloomFilter;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BloomFilterExample {

    public static void main(String[] args) throws IOException {

        File booksPipeDelimited = new File("src/main/resources/books.data");

        List<Book> books = Files
                .asCharSource(booksPipeDelimited, Charsets.UTF_8)
                .readLines(new LineProcessor<List<Book>>() {

            Splitter splitter = Splitter.on("|");

            List<Book> books = Lists.newArrayList();

            @Override
            public boolean processLine(String line) throws IOException {
                List<String> parts = Lists.newArrayList(splitter.split(line));

                Book book = new Book(
                        parts.get(0),
                        parts.get(1),
                        parts.get(2),
                        parts.get(3),
                        Double.parseDouble(parts.get(4)));

                books.add(book);

                return true;
            }

            @Override
            public List<Book> getResult() {
                return books;
            }
        });

        BloomFilter<Book> bloomFilter = BloomFilter.create(BookFunnel.FUNNEL, 5);

        for (Book book: books) {
            bloomFilter.put(book);
        }

        Book newBook = new Book(
                "author",
                "Mountain Climbing",
                "publisher",
                "isbn",
                20);

        System.out.println("book " + newBook.getTitle() + " contained " + bloomFilter.mightContain(newBook));

        Book book1 = books.get(0);

        System.out.println("book " + book1.getTitle() + " contained " + bloomFilter.mightContain(book1));

    }

}
