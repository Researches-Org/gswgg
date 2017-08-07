package chapter05;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FutureCallbackSample {

    private static final int NUM_THREADS = 10;

    private ListeningExecutorService executorService;

    public FutureCallbackSample() {
        executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(NUM_THREADS));
    }

    protected void submitTask(Callable<String> callable, FutureCallback<String> futureCallback) {
        ListenableFuture<String> listenableFuture = executorService.submit(callable);

        Futures.addCallback(listenableFuture, futureCallback, executorService);
    }

    public static void main(String[] args) throws InterruptedException {
        FutureCallbackSample fcs = new FutureCallbackSample();

        while (true) {
            fcs.submitTask(new RandomStringCallable(), new FutureCallbackImpl());

            System.out.println(">>> Task submitted");

            Thread.currentThread().sleep(1000);
        }

    }



}
