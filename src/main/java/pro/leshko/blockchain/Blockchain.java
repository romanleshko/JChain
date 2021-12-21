package pro.leshko.blockchain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Blockchain {
    private final List<Block> chain = new LinkedList<>();
    private static final BigDecimal REWARD = BigDecimal.valueOf(50L);

    private static final int DIFFICULTY = 2;
    private final List<Transaction> pending = new ArrayList<>();

    public Blockchain() {
        final Block genesis = new Block();
        genesis.mine(DIFFICULTY);
        this.chain.add(genesis);
    }

    public List<Block> getChain() { // Only for testing/messing around
        return chain;
    }

    public void print() {
        System.out.println("==== Blockchain Begin ====");
        chain.forEach(System.out::println);
        System.out.println("==== Blockchain End ==== Status: " + (this.isValid() ? "Valid" : "Invalid"));
    }

    public Block getLastBlock() {
        return chain.get(chain.size() - 1);
    }

    public boolean isValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block prev = chain.get(i - 1);
            Block cur = chain.get(i);

            if (!prev.getHash().equals(cur.getPrevHash()))
                return false;
        }
        return true;
    }

    public void mineBlock(final String rewardAddress) {
        final List<Transaction> finalTransactions = new ArrayList<>(this.pending.size() + 1);
        finalTransactions.addAll(this.pending);
        finalTransactions.add(new Transaction("", rewardAddress, REWARD));

        final Block block = new Block(finalTransactions);
        block.setPrevHash(this.getLastBlock().getHash());

        System.out.println("Mining block...");
        block.mine(DIFFICULTY);
        System.out.println("Done!");
        this.pending.clear();
        this.chain.add(block);
    }

    public void addTransaction(final Transaction transaction) {
        this.pending.add(transaction);
    }

    public BigDecimal getBalance(final String address) {
        return this.chain.stream()
                .map(Block::getTransactions)
                .flatMap(Collection::stream)
                .reduce(BigDecimal.ZERO, (acc, transaction) -> {
                    if (transaction.getFrom().equals(address))
                        return acc.add(transaction.getAmount().negate());
                    else if (transaction.getTo().equals(address))
                        return acc.add(transaction.getAmount());
                    else
                        return acc.add(BigDecimal.ZERO);
                }, BigDecimal::add);
    }
}
