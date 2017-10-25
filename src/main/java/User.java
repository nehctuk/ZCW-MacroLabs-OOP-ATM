import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Account> accounts = new ArrayList<Account>();

    protected int getNumberOfAccounts() {
        return accounts.size();
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account getSpecificAccount(int index) {
        Account output = null;

        for (Account account:accounts) {
            if (account.getAccountNum() == index) {
                output = account;
                return output;
            }
        }

        return output;
    }

    protected void createAccount(String accountType) {
        Account newAccount = new Account(accountType);
        accounts.add(newAccount);
    }

    protected void createAccount(String accountType, double balance) {
        Account newAccount = new Account(accountType, balance);
        accounts.add(newAccount);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    protected String getAccounts() {
        String output = "";
        for (Account account : accounts) {
            output +=
                    "Account Number: "+account.getAccountNum()+
                    ", Account Type: " + account.getAccountType() +
                    ", Account Balance: " + String.format("$%,.2f", account.getBalance()) + "\n";
        }

        return output;
    }

}

