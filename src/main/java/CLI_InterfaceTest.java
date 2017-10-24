import org.junit.Assert;
import org.junit.Test;

public class CLI_InterfaceTest {

    @Test
    public void WelcomeMessageTest() {
        String expected =
                "Welcome to the ATM!\n" +
                "Are you new User?";
    }

    @Test
    public void printIntroMenuTest_InputNo() {
        String expected =
                "Please select an option from the menu below!\n"+
                "0: Withdraw\n"+
                "1: Deposit\n"+
                "2: Check Account Balance\n"+
                "3: Create New Account\n"+
                "4: Delete Existing Account\n"+
                "5: Logout";
        String actual = CLI_Interface.printIntroMenu("No");
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void printIntroMenuTest_InputYes() {
        String expected = "Would you like to to create an account?";
        String actual = CLI_Interface.printIntroMenu("Yes");
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void printIntroMenuTest_InputOther() {
        String answer = "Other";
        String expected = "Please return 'Yes' or 'No' not: "+answer;
        String actual = CLI_Interface.printIntroMenu(answer);
        Assert.assertEquals(expected,actual);
    }



}
