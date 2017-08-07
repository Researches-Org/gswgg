package chapter05;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.SettableFuture;

import javax.annotation.Nullable;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;

public class AsyncFunctionSample implements AsyncFunction<Long, String> {

    private ConcurrentMap<Long, String> map = Maps.newConcurrentMap();

    private ListeningExecutorService listeningExecutorService;

    @Override
    public ListenableFuture<String> apply(@Nullable Long input) throws Exception {

        if (map.containsKey(input)) {
            SettableFuture<String> listenableFuture = SettableFuture.create();

            listenableFuture.set(map.get(input));

            return listenableFuture;
        }

        return listeningExecutorService.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                String retrieved = input.toString();
                map.putIfAbsent(input, retrieved);
                return retrieved;
            }

        });
    }
}
