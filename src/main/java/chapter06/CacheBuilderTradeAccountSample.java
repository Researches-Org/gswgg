package chapter06;

import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CacheBuilderTradeAccountSample {

    private LoadingCache<String, TradeAccount> tradeAccountCache;

    private TradeAccountService tradeAccountService;

    public CacheBuilderTradeAccountSample(TradeAccountService tradeAccountService) {
        init();
        this.tradeAccountService = tradeAccountService;
    }

    private void init() {
        tradeAccountCache = CacheBuilder
                .newBuilder()
                .expireAfterWrite(5L, TimeUnit.MINUTES)
                .maximumSize(5000L)
                .removalListener(new TradeAccountRemovalListener())
                .ticker(Ticker.systemTicker())
                .recordStats()
                .build(new CacheLoader<String, TradeAccount>() {
                    @Override
                    public TradeAccount load(String id) throws Exception {
                        return tradeAccountService.getTradeAccountById(id);
                    }
                });
    }

    public LoadingCache<String, TradeAccount> getTradeAccountCache() {
        return tradeAccountCache;
    }

    public static void main(String[] args) throws ExecutionException {
        CacheBuilderTradeAccountSample cbs = new CacheBuilderTradeAccountSample(new TradeAccountService());

        for (int i = 1; i <= 10000; i++) {
            TradeAccount tradeAccount = cbs.getTradeAccountCache().get(i + "");
            System.out.println(tradeAccount);
        }

        CacheStats cacheStats = cbs.getTradeAccountCache().stats();

        System.out.println(">>>>>>>>>>>>>>>>>>>> Cache Stats <<<<<<<<<<<<<<<<<");
        System.out.println("Average time spent loading new values.............: " + cacheStats.averageLoadPenalty());
        System.out.println("Fraction of requests to the cache that were hits..: " + cacheStats.hitCount());
        System.out.println("Fraction of requests to the cache that were misses: " + cacheStats.missCount());
        System.out.println("Number of evictions made by the cache.............: " + cacheStats.evictionCount());

    }


}
