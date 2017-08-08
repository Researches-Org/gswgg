package chapter07;

import chapter06.TradeAccount;
import com.google.common.eventbus.EventBus;

import java.util.Date;

public class BuySellTradeExecutor {

    private EventBus eventBus;

    public BuySellTradeExecutor(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void executeTrade(TradeAccount tradeAccount, double amount, TradeType tradeType) {
        TradeAccountEvent tradeAccountEvent = processTrade(tradeAccount, amount, tradeType);
        eventBus.post(tradeAccountEvent);

    }

    private TradeAccountEvent processTrade(TradeAccount tradeAccount, double amount, TradeType tradeType) {

        Date executionTime = new Date();

        String message = String.format("Processed trade for %s of amount %n type %s @ %s", tradeAccount, amount,
                tradeType, executionTime);

        TradeAccountEvent tradeAccountEvent;

        if (tradeType == TradeType.BUY) {
            tradeAccountEvent = new BuyEvent(tradeAccount, amount, executionTime);
        } else {
            tradeAccountEvent = new SellEvent(tradeAccount, amount, executionTime);
        }

        System.out.println(message);

        return tradeAccountEvent;
    }

}

