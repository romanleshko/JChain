package pro.leshko.blockchain;

import java.time.Instant;
import java.util.Objects;

public class Block {

    private Instant timestamp;
    private String data;
    private String prevHash;

    public Block(final Instant timestamp, final String data) {
        this.timestamp = timestamp;
        this.data = data;
    }

    public Block(final Instant timestamp, final String data, final String prevHash) {
        this(timestamp, data);
        this.prevHash = prevHash;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public void setPrevHash(String prevHash) {
        this.prevHash = prevHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return Objects.equals(timestamp, block.timestamp) &&
                Objects.equals(data, block.data) &&
                Objects.equals(prevHash, block.prevHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, data, prevHash);
    }

    @Override
    public String toString() {
        return String.format(
                "Block {timestamp='%s', data='%s', prevHash='%s'}", this.timestamp, this.data, this.prevHash);
    }
}
