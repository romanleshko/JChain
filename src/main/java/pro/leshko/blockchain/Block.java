package pro.leshko.blockchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Block {

    private Instant timestamp;
    private List<Transaction> transactions = Collections.emptyList();
    private String prevHash;
    private String hash;
    private long nonce;

    public Block() {
        this.timestamp = Instant.now();
        this.hash = "";
    }

    public Block(final List<Transaction> transactions) {
        this.timestamp = Instant.now();
        this.transactions = transactions;
        this.hash = "";
    }

    public Block(final List<Transaction> transactions, final String prevHash) {
        this(transactions);
        this.prevHash = prevHash;
    }

    public Block(final Instant timestamp, final List<Transaction> transactions, final String prevHash) {
        this(transactions, prevHash);
        this.timestamp = timestamp;
    }

    public void computeHash() {
        final MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
            return;
        }

        assert md != null;
        final byte[] digest = md.digest(this.toString().getBytes(StandardCharsets.UTF_8));

        final StringBuilder hexHash = new StringBuilder(2 * digest.length);
        for (byte b : digest)
            hexHash.append(String.format("%02x", b));

        this.hash = hexHash.toString();
    }

    public void mine(final int difficulty) {
        while (this.hash.isBlank() || !this.hash.substring(0, difficulty).equals("8".repeat(difficulty))) {
            this.computeHash();
            this.nonce++;
        }
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public void setPrevHash(String prevHash) {
        this.prevHash = prevHash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public long getNonce() {
        return nonce;
    }

    public void setNonce(long nonce) {
        this.nonce = nonce;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTrasactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return nonce == block.nonce &&
                Objects.equals(timestamp, block.timestamp) &&
                Objects.equals(transactions, block.transactions) &&
                Objects.equals(prevHash, block.prevHash) &&
                Objects.equals(hash, block.hash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, transactions, prevHash, hash, nonce);
    }

    @Override
    public String toString() {
        return String.format(
                "Block {timestamp='%s', transactions='%s', prevHash='%s', hash='%s', nonce='%s'}", this.timestamp, this.transactions, this.prevHash, this.hash, this.nonce);
    }
}
