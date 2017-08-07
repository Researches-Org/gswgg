package chapter05;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ListenableFutureSample {

    private static final int NUM_THREADS = 10;

    private ListeningExecutorService executorService;

    public ListenableFutureSample() {
        executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(NUM_THREADS));
    }

    protected void submitTask(Callable<String> callable, Runnable runnable) {
        ListenableFuture<String> listenableFuture = executorService.submit(callable);
        listenableFuture.addListener(runnable, executorService);
    }

    public static void main(String[] args) throws InterruptedException {
        ListenableFutureSample lfs = new ListenableFutureSample();

        lfs.submitTask(new RandomStringCallable(), new Runnable() {
            @Override
            public void run() {
                System.out.println("Task executed.");
            }
        });

        lfs.executorService.awaitTermination(10000, TimeUnit.MILLISECONDS);

        System.exit(0);
    }


}
