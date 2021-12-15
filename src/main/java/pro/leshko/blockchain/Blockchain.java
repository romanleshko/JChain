package pro.leshko.blockchain;

import java.util.LinkedList;
import java.util.List;

public class Blockchain {
    private final List<Block> chain = new LinkedList<>();

    public Blockchain() {
        this.chain.add(new Block("Genesis"));
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

            if (prev.hashCode() != cur.getPrevHash())
                return false;
        }
        return true;
    }

    public void addBlock(final Block newBlock) {
        newBlock.setPrevHash(this.getLastBlock().hashCode());
        chain.add(newBlock);
    }
}
