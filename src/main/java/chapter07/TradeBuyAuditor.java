package chapter07;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.List;

public class TradeBuyAuditor {

    private List<BuyEvent> buyEvents = Lists.newArrayList();

    public TradeBuyAuditor(EventBus eventBus) {
        eventBus.register(this);
    }

    @Subscribe
    public void auditBuy(BuyEvent buyEvent) {
        buyEvents.add(buyEvent);
        System.out.println("Received Trade Buy Event " + buyEvent);
    }

    public List<BuyEvent> getBuyEvents() {
        return buyEvents;
    }
}
