package chapter07;

import chapter06.TradeAccount;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

import java.util.Date;

public class TradeAccountEvent {

    private double amount;

    private Date tradeExecutionTime;

    private TradeType tradeType;

    private TradeAccount tradeAccount;

    public TradeAccountEvent(TradeAccount tradeAccount, double amount, Date tradeExecutionTime, TradeType tradeType) {
        Preconditions.checkArgument(amount > 0.0, "trade can't be less than zero");
        this.amount = amount;
        this.tradeExecutionTime = Preconditions.checkNotNull(tradeExecutionTime, "Execution time can't be null");
        this.tradeAccount = Preconditions.checkNotNull(tradeAccount, "Trade account can't be null");
        this.tradeType = Preconditions.checkNotNull(tradeType, "Trade type can't be null.");
    }

    public double getAmount() {
        return amount;
    }

    public Date getTradeExecutionTime() {
        return tradeExecutionTime;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public TradeAccount getTradeAccount() {
        return tradeAccount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount, tradeExecutionTime, tradeType, tradeAccount);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != getClass()) return false;
        TradeAccountEvent event = (TradeAccountEvent) obj;
        return Objects.equal(amount, event.getAmount())
                && Objects.equal(tradeExecutionTime, event.getTradeExecutionTime())
                && Objects.equal(tradeType, event.getTradeType())
                && Objects.equal(tradeAccount, event.getTradeAccount());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("amount", amount)
                .add("tradeExecutionTime", tradeExecutionTime)
                .add("tradeType", tradeType)
                .add("tradeAccount", tradeAccount)
                .toString();
    }
}
