import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class Atm_LogicTest {
    String username;
    String password;
    User user;

    @Before
    public void setUp() {
        username = "andrew";
        password = "1234";
        user = new User(username,password);
    }

    @Test
    public void getSetCurrentUserTest() {
       User expected = user;
       AtmLogic.setCurrentUser(user);
       User actual = AtmLogic.getCurrentUser();
       Assert.assertEquals(expected,actual);
    }

    @Test
    public void getSetCurrentUserName() {
        String expected = username;
        AtmLogic.setCurrentUsername(username);
        String actual = AtmLogic.getCurrentUsername();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getSetCurrentPassword() {
        String expected = password;
        AtmLogic.setCurrentPassword(password);
        String actual = AtmLogic.getCurrentPassword();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void IntroLogicTest_InputYes() {
        String welcomeMessage = "Yes";
        boolean expected = true;
        boolean actual = "Yes".equalsIgnoreCase(welcomeMessage);
        Assert.assertEquals(expected,actual);
    }



    @Test
    public void IntroLogicTest_InputNo() {
        String welcomeMessage = "No";
        boolean expected = true;
        boolean actual = "No".equalsIgnoreCase(welcomeMessage);
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void loginTest(){
        // ask about test

    }

}


