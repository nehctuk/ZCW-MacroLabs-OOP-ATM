public class UserFactory {
    public static void addNewUser (User user) {
        UserWarehouse.addUser(user);
        AtmLogic.setCurrentUser(user);
    }

    public static void addNewUser (String username, String password) {
        User user = new User(username, password);
        UserWarehouse.addUser(user);
        AtmLogic.setCurrentUsername(username);
        AtmLogic.setCurrentPassword(password);
    }

}

