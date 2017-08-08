package chapter07;

import chapter06.TradeAccount;

import java.util.Date;

public class BuyEvent extends TradeAccountEvent {

    public BuyEvent(TradeAccount tradeAccount, double amount, Date tradeExecutionTime) {
        super(tradeAccount, amount, tradeExecutionTime, TradeType.BUY);
    }
}
