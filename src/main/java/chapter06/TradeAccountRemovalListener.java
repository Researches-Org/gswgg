package chapter06;

import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class TradeAccountRemovalListener implements RemovalListener<String, TradeAccount> {

    @Override
    public void onRemoval(RemovalNotification<String, TradeAccount> removalNotification) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>> Trade account removed:");
        System.out.println(">>>>>>>>>>>>>> id: " + removalNotification.getKey());
        System.out.println(">>> trade account: " + removalNotification.getValue());
        System.out.println();
    }
}
