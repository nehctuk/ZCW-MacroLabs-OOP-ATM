import java.util.ArrayList;

public class UserWarehouse {
    private static ArrayList<User> user = new ArrayList<User>();

    public static ArrayList<User> getUsers() {
        return user;
    }

    public static void addUser(User user) {
        UserWarehouse.user.add(user);
    }
}

