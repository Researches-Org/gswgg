package chapter06;

import chapter02.Book;
import com.google.common.collect.MapMaker;

import java.util.concurrent.ConcurrentMap;

public class MapMakerSample {

    private ConcurrentMap<String, Book> books;

    public MapMakerSample() {

        // When using WeakReferences for either keys or
        // values, if one is garbage-collected, the entire entry is removed from the map; partial
        // entries are never exposed to the client.
        books = new MapMaker()
                .concurrencyLevel(2)
                .weakValues()
                .makeMap();
    }

    private static void sleep(long time) {
        try {
            Thread.currentThread().sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static class PrintSizeRunnable implements Runnable {
        private ConcurrentMap<String, Book> books;

        PrintSizeRunnable(ConcurrentMap<String, Book> books) {
            this.books = books;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println(">>> SIZE: " + books.size());
                sleep(1000);
            }
        }
    }

    private static class BookRunnable implements Runnable {

        private int id;

        private ConcurrentMap<String, Book> books;

        BookRunnable(int id, ConcurrentMap<String, Book> books) {
            this.id = id;
            this.books = books;
        }

        @Override
        public void run() {

            long count = 0;

            while (true) {
                String key = id + "-" + count++;
                books.put(key, new Book(key, key, key, key, id));
                sleep(1000);
            }
        }
    }

    public static void main(String[] args) {

        MapMakerSample mms = new MapMakerSample();

        Thread t1 = new Thread(new BookRunnable(1, mms.books));
        Thread t2 = new Thread(new BookRunnable(2, mms.books));
        Thread t3 = new Thread(new BookRunnable(3, mms.books));
        Thread t4 = new Thread(new PrintSizeRunnable(mms.books));

        t4.start();
        t1.start();
        t2.start();
        t3.start();

    }

}
