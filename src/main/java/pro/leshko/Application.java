package pro.leshko;

import pro.leshko.blockchain.Block;
import pro.leshko.blockchain.Blockchain;

public class Application {
    public static void main(String[] args) {
        System.out.println("JChain started");
        final Blockchain blockchain = new Blockchain();
        blockchain.addBlock(new Block("First block"));
        blockchain.addBlock(new Block("Second block"));

        blockchain.print();
        blockchain.getChain().get(1).setData("Let's break the chain");
        blockchain.print();

    }
}
