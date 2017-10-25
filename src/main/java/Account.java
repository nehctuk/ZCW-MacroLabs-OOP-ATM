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

    public String getAccountType() { return accountType; }
    public void setBalance(double balance) { this.balance = balance; }
    public double getBalance() { return balance; }
    public static void setTotalAccountNum(int totalAccountNum) { Account.totalAccountNum = totalAccountNum; }
    public int getAccountNum() { return accountNum; }
    public ArrayList<String> getTransactions() { return transactions; }

    public Account(String accountType) {
        this(accountType, 0);
    }

    public Account(String accountType, double balance) {
        this(accountType, balance, totalAccountNum);
    }

    private Account(String accountType, double balance, int accountNum){
        this.accountType = accountType;
        this.balance = balance;
        this.accountNum = accountNum;
    }



//    @Override
//    public String toString() {
//        return "Account: " +
//                "accountNum: " + accountNum +
//                ", balance: " + String.format("$%.2f", balance) +
//                ", transactions: " + transactions +
//                ", accountType: '" + accountType + '\'';
//    }
}

