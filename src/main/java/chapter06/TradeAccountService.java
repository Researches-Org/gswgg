package chapter06;

import com.google.common.collect.Maps;

import java.util.Map;

public class TradeAccountService {

    private Map<String, TradeAccount> trades;

    public TradeAccountService() {
        trades = Maps.newHashMap();
        loadTrades();
    }

    private void loadTrades() {

        for (int i = 1; i <= 1000; i++) {
            trades.put(i + "", new TradeAccount(i + "", i + "", i));
        }

    }

    private TradeAccount createAndReturnTrade(String id) {
        return new TradeAccount(id, id, 1);
    }

    public TradeAccount getTradeAccountById(String id) {
        return trades.getOrDefault(id, createAndReturnTrade(id));
    }

}