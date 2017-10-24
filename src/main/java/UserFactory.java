public class UserFactory {

    public static void addNewUser (String username, String password) {
        User user = new User(username, password);
        UserWarehouse.addUser(user);
        CLI_Logic.setCurrentUsername(username);
        CLI_Logic.setCurrentPassword(password);
    }


}

