package pro.leshko;

import pro.leshko.blockchain.Blockchain;
import pro.leshko.blockchain.Transaction;

import java.math.BigDecimal;

public class Application {
    public static void main(String[] args) {
        System.out.println("JChain started");
        final Blockchain blockchain = new Blockchain();
        blockchain.addTransaction(new Transaction("john", "susan", BigDecimal.valueOf(15L)));
        blockchain.addTransaction(new Transaction("susan", "john", BigDecimal.valueOf(35L)));

        blockchain.mineBlock("roman's address");
        System.out.println("My (miner) balance is: " + blockchain.getBalance("roman's address"));
        System.out.println("John's balance is: " + blockchain.getBalance("john"));
        System.out.println("Susan's balance is: " + blockchain.getBalance("susan"));


        blockchain.print();

    }
}
