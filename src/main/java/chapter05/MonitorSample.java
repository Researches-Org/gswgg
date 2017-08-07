package chapter05;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Monitor;

import java.util.List;

public class MonitorSample {

    private List<String> list = Lists.newArrayList();

    private static final int MAX_SIZE = 10;

    private Monitor monitor = new Monitor();

    private Monitor.Guard listBellowCapacity = new Monitor.Guard(monitor) {
        @Override
        public boolean isSatisfied() {
            return list.size() < MAX_SIZE;
        }
    };

    public void addToList(String item) throws InterruptedException {
        // Only one thread can enter a Monitor block at any time.
        // The same thread can enter and exit the same Monitor block any
        // number of times but each entry must be followed by an exit.
        monitor.enterWhen(listBellowCapacity);
        try {
            list.add(item);
        } finally {
            monitor.leave();
        }
    }


}
