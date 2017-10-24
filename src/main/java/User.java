import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Account> accounts = new ArrayList<Account>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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

    protected ArrayList<Account> getAccounts() {
        return accounts;
    }

}

