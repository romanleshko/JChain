package pro.leshko;

import pro.leshko.blockchain.Block;

import java.time.Instant;

public class Application {
    public static void main(String[] args) {
        System.out.println("JChain started");
        final Block block = new Block(Instant.now(), "hello");
        System.out.println(block.hashCode());
        System.out.println(block);
    }
}
