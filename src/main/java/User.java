import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Account> accounts;

    private void createAccount(String accountType) {
        Account newAccount = new Account(accountType);
        accounts.add(newAccount);
    }

    private void createAccount(String accountType, double balance) {
        Account newAccount = new Account(accountType, balance);
        accounts.add(newAccount);
    }

}

