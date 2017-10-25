import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class AccountTest {
    int accountNum;
    double balance;
    String accountType;
    //ArrayList<Transanctions> transanctions;
    Account account;


    @Before
    public void setUp(){
        accountType = "savings";
        balance = 1000;
        account = new Account(accountType, balance);
        accountNum = account.getAccountNum();
    }

    @After
    public void cleanUp(){
         Account.setTotalAccountNum(0);
    }

    @Test
    public void getAccountTypeTest() {
        String expected = "savings";
        String actual = account.getAccountType();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getSetBalanceTest() {
        double expected = 2000;
        account.setBalance(expected);
        double actual = account.getBalance();
        Assert.assertEquals(expected, actual, 0.001);
    }

    @Test
    public void getAccountNumTest() {
        int expected = 0;
        int actual = account.getAccountNum();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getTransactionsTest() {
        ArrayList<String> expected = new ArrayList<String>();
        String withdraw = "Withdrew $100.00 from Savings: Account #0";
        String deposit = "Deposited $100.00 into Savings: Account #0";
        String transfer =
                "Transferred $100.00 from Savings: Account #0.\n"
                +"Into Checking: Account #1";

        expected.add(withdraw);
        expected.add(deposit);
        expected.add(transfer);

        ArrayList<String> actual = account.getTransactions();

        Assert.assertEquals(expected,actual);


    }

    @Test
    public void accountConstructorTest() {
        Account account = new Account("savings", 1000.00);
        String expected = "savings";
        String actual = account.getAccountType();
        double expectedBal = 1000.00;
        double actualBal = account.getBalance();

        Assert.assertEquals(expected,actual);
        Assert.assertEquals(expectedBal,actualBal, 0.001);
    }
}
