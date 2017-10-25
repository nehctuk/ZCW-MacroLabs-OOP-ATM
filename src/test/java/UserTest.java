import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class UserTest {
    User newUser;
    String username;
    String password;
    String accountType;
    double balance;
    ArrayList<Account> accounts;

    @Before
    public void setUp() {
        username = "Brian";
        password = "1234";
        accountType = "savings";
        balance = 1000;
        newUser = new User(username, password);
        accounts = new ArrayList<Account>();
    }

    @Test
    public void getSetUsername() {
        String expected = newUser.getUsername();
        User actualUser = new User("Andrew", "1234");
        actualUser.setUsername(username);
        String actual = actualUser.getUsername();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getSetPassword() {
        String expected = newUser.getPassword();
        User actualUser = new User("Andrew", "1234");
        actualUser.setPassword(password);
        String actual = actualUser.getPassword();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAccountsTest() {
        Assert.assertNotNull(newUser.getAccounts());
    }

    @Test
    public void getNumberOfAccountsTest() {
        int expected = 0;
        int actual = newUser.getNumberOfAccounts();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void createAccountTest() {
        Account expectedUser = new Account(accountType, balance);

        newUser.createAccount(accountType, balance);
        ArrayList<Account> actualAccounts = newUser.getAccounts();

        String expectedType = expectedUser.getAccountType();
        double expectedBalance = expectedUser.getBalance();

        String actualType = actualAccounts.get(0).getAccountType();
        double actualBalance = actualAccounts.get(0).getBalance();

        Assert.assertEquals(expectedType, actualType);
        Assert.assertEquals(expectedBalance, actualBalance, 0.001);
    }

    @Test
    public void getSpecificAccountTest() {
        newUser.createAccount(accountType, balance);
        Account expected = newUser.getAccounts().get(0);
        Account actual = newUser.getSpecificAccount(0);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAccountsToStringTest() {
        newUser.createAccount(accountType, balance);

        String expected = "Account Number: 0" +
                ", Account Type: savings" +
                ", Account Balance: $1,000.00" +
                "\n";


        String actual = "Account Number: "+newUser.getSpecificAccount(0).getAccountNum()+
                ", Account Type: " + newUser.getSpecificAccount(0).getAccountType() +
                ", Account Balance: " + String.format("$%,.2f", newUser.getSpecificAccount(0)
                .getBalance()) + "\n";

        Assert.assertEquals(expected,actual);

    }



}
