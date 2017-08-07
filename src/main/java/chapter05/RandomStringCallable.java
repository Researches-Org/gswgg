package chapter05;

import java.util.Random;
import java.util.concurrent.Callable;

public class RandomStringCallable implements Callable<String> {

    private static final int SLEEP_TIME = 2000;

    private static final int LETTERS = 26;

    private static final int A = 97;

    private static final int SIZE = 5;

    @Override
    public String call() throws Exception {
        System.out.println(">>> Executing callback " + RandomStringCallable.class);

        Thread.currentThread().sleep(SLEEP_TIME);

        return buildString();
    }

    private String buildString() {
        Random random = new Random();

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= SIZE; i++) {
            char c =  (char)(random.nextInt(LETTERS) + A);
            builder.append(c);
        }

        return builder.toString();
    }
}
