import org.junit.Assert;
import org.junit.Test;

public class AccountTest {
    @Test
    public void accountConstructorTest() {
        Account account = new Account("savings");
        Account account1 = new Account("savings", 1000.00);
        String expected = "savings";
        String actual = account.getAccountType();
        double expectedBal = 1000.00;
        double actualBal = account1.getBalance();

        Assert.assertEquals(expected,actual);
        Assert.assertEquals(expectedBal,actualBal, 0.001);
    }
}
