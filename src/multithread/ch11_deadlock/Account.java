package multithread.ch11_deadlock;

/**
 * Created by zoush on 2017/5/2.
 */
public class Account {
    private int balance = 10000;

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public static void transfer(Account accFrom, Account accTo, int amount) {
        accFrom.withdraw(amount);
        accTo.deposit(amount);
    }
}
