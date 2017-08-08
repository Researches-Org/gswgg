package chapter07;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.logging.Logger;

public class DeadEventSubscriber {

    private static final Logger logger = Logger.getLogger(DeadEventSubscriber.class.getName());

    public DeadEventSubscriber(EventBus eventBus) {
        eventBus.register(this);
    }

    @Subscribe
    public void handleUnsubscribedEvent(DeadEvent deadEvent) {
        logger.warning("No subscribers for " + deadEvent.getEvent());
    }

}
