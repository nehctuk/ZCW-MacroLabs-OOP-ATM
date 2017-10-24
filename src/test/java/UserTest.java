import org.junit.Assert;
import org.junit.Test;

public class UserTest {
    @Test
    public void userTest_Constructor() {
        User newUser = new User("Brian", "1234");
        String expectedUser = "Brian";
        String expectedPass = "1234";
        String actualUser = newUser.getUsername();
        String actualPass = newUser.getPassword();

        Assert.assertEquals(expectedUser, actualUser);
        Assert.assertEquals(expectedPass, actualPass);
    }
}
