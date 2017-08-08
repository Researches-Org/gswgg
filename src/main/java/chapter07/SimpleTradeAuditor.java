package chapter07;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.List;

public class SimpleTradeAuditor {

    private List<TradeAccountEvent> tradeEvents = Lists.newArrayList();

    public SimpleTradeAuditor(EventBus eventBus) {
        eventBus.register(this);
    }

    @Subscribe
    public void auditTrade(TradeAccountEvent tradeAccountEvent) {
        tradeEvents.add(tradeAccountEvent);
        System.out.println("Received trade: " + tradeAccountEvent);
    }
}
