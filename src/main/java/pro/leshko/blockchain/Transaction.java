package pro.leshko.blockchain;

import java.math.BigDecimal;
import java.util.Objects;

public class Transaction {
    private String from;
    private String to;
    private BigDecimal amount;

    public Transaction(final String from, final String to, final BigDecimal amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(from, that.from) &&
                Objects.equals(to, that.to) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, amount);
    }


    @Override
    public String toString() {
        return String.format(
                "Transaction {from='%s', to='%s', amount='%s'}", this.from, this.to, this.amount);
    }
}
