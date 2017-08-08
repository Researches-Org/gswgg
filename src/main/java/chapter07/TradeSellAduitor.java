package chapter07;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.List;

public class TradeSellAduitor {

    private List<SellEvent> sellEvents = Lists.newArrayList();

    public TradeSellAduitor(EventBus eventBus) {
        eventBus.register(this);
    }

    @Subscribe
    public void auditSell(SellEvent sellEvent) {
        sellEvents.add(sellEvent);
        System.out.println("Received Sell Event " + sellEvent);
    }

    public List<SellEvent> getSellEvents() {
        return sellEvents;
    }
}
