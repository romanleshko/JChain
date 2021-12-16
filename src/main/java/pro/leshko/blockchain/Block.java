package pro.leshko.blockchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Objects;

public class Block {

    private Instant timestamp;
    private String data;
    private String prevHash;
    private String hash;
    private long nonce;

    public Block(final String data) {
        this.timestamp = Instant.now();
        this.data = data;
        this.hash = "";
    }

    public Block(final String data, final String prevHash) {
        this(data);
        this.prevHash = prevHash;
    }

    public Block(final Instant timestamp, final String data, final String prevHash) {
        this(data, prevHash);
        this.timestamp = timestamp;
    }

    public void computeHash() {
        final MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return;
        }

        assert md != null;
        byte[] digest = md.digest(this.toString().getBytes(StandardCharsets.UTF_8));

        final StringBuilder hexHash = new StringBuilder(2 * digest.length);
        for (byte b : digest) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                hexHash.append("0");

            hexHash.append(hex);
        }
        this.hash = hexHash.toString();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return nonce == block.nonce &&
                Objects.equals(timestamp, block.timestamp) &&
                Objects.equals(data, block.data) &&
                Objects.equals(prevHash, block.prevHash) &&
                Objects.equals(hash, block.hash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, data, prevHash, hash, nonce);
    }

    @Override
    public String toString() {
        return String.format(
                "Block {hash='%s' timestamp='%s', data='%s', prevHash='%s', nonce='%s'}", this.hash, this.timestamp, this.data, this.prevHash, this.nonce);
    }
}
