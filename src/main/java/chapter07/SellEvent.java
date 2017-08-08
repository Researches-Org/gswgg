package chapter07;

import chapter06.TradeAccount;

import java.util.Date;

/**
 * Created by manoel on 8/8/17.
 */
public class SellEvent extends TradeAccountEvent {


    public SellEvent(TradeAccount tradeAccount, double amount, Date tradeExecutionTime) {
        super(tradeAccount, amount, tradeExecutionTime, TradeType.SELL);
    }
}
