package chapter06;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class TradeAccount {

    private String id;

    private String owner;

    private double balance;

    public TradeAccount(String id, String owner, double balance) {
        this.id = Preconditions.checkNotNull(id, "id can't be null");
        this.owner = Preconditions.checkNotNull(owner, "owner can't be null");
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, owner, balance);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != getClass()) return false;
        TradeAccount trade = (TradeAccount) obj;
        return Objects.equal(id, trade.getId())
                && Objects.equal(owner, trade.getOwner())
                && Objects.equal(balance, trade.getBalance());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("owner", owner)
                .add("balance", balance)
                .toString();
    }
}
