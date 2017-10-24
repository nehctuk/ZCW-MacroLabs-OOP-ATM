import java.util.ArrayList;

public class Account {
    private static int totalAccountNum = 0;
    private int accountNum;
    private double balance;
    private ArrayList<String> transactions;
    private String accountType;

    {
        totalAccountNum++;
    }

    public Account(String accountType) {
        this.accountType = accountType.toLowerCase();
        this.balance = 0.0;
        this.accountNum = totalAccountNum;
    }

    public Account(String accountType, double balance) {
        this(accountType);
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account: " +
                "accountNum=" + accountNum +
                ", balance=" + String.format("$%.2f", balance) +
                ", transactions=" + transactions +
                ", accountType='" + accountType + '\'';
    }
}

