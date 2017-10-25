import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class UserWarehouseTest {
    ArrayList<User> userArrayList;
    User user;
    String username;
    String password;

    @Before
    public void setUp() {
        userArrayList = new ArrayList<User>();
        username = "andrew";
        password = "1234";
        user = new User(username,password);
    }

    @Test
    public void addGetUserWarehouse() {
        User user = new User(username,password);
        String expectedUsername = user.getUsername();
        String expectedPassword = user.getPassword();

        UserWarehouse.addUser(user);
        String actualUsername = UserWarehouse.getUsers().get(0).getUsername();
        String actualPassword = UserWarehouse.getUsers().get(0).getPassword();

        Assert.assertEquals(expectedUsername, actualUsername);
        Assert.assertEquals(expectedPassword, actualPassword);
    }
}
