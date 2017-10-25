import java.util.Scanner;


public class CLI_Logic {
    private static String currentUsername;
    private static String currentPassword;
    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        CLI_Logic.currentUser = currentUser;
    }

    public static String getCurrentUsername() {
        return CLI_Logic.currentUsername;
    }

    public static void setCurrentUsername(String username) {
        CLI_Logic.currentUsername = username;
    }

    public static String getCurrentPassword() {
        return CLI_Logic.currentPassword;
    }

    public static void setCurrentPassword(String password) {
        CLI_Logic.currentPassword = password;
    }

    public static void IntroLogic() {
        String welcomeMessage = CLI_Interface.welcomeMessage();
        if ("Yes".equalsIgnoreCase(welcomeMessage)) {
            CLI_Interface.createNewUser();
            CLI_Interface.createNewBankAccount();
            CLI_Logic.menuSelection();
        } else if ("No".equalsIgnoreCase(welcomeMessage)) {
            CLI_Logic.login();
            CLI_Logic.menuSelection();
        }
        else {
            CLI_Interface.InvalidResponse(welcomeMessage);
            CLI_Logic.IntroLogic();
            }
        }

    private static void login() {
        CLI_Interface.getLoginDetails();
        String currentUser = CLI_Logic.getCurrentUsername();
        String currentPassword = CLI_Logic.getCurrentPassword();
        boolean accountFound = false;

        for (User user: UserWarehouse.getUsers()) {
            if (currentUser.equalsIgnoreCase(user.getUsername()) &&
                    currentPassword.equals(user.getPassword())) {
                CLI_Logic.setCurrentUser(user);
                CLI_Interface.printIntroMenu();
                accountFound = true;
            }

        }
        if (!accountFound){
            String response = CLI_Interface.loginFalure();

            if ("Yes".equalsIgnoreCase(response)) {
                CLI_Logic.login();
            }
            else if ("No".equalsIgnoreCase(response)) {
                CLI_Logic.IntroLogic();
            }
            else {
                CLI_Interface.InvalidResponse(response);
            }
        }
    }


    public static void addAccountToUser(String accountType, double balance) {
        currentUser.createAccount(accountType, balance);
    }

    public static void menuSelection() {
        int selection = Integer.parseInt(CLI_Interface.getStringInput());
        if (selection == 0) {
            CLI_Interface.withDrawAttempt();
            CLI_Interface.printIntroMenu();
            CLI_Logic.menuSelection();
        }
        else if (selection == 3) {
            CLI_Interface.createNewBankAccount();
            CLI_Logic.menuSelection();
        }
        else if (selection == 7) {
            CLI_Logic.logout();
        }
    }

    public static void logout() {
        CLI_Interface.exitMessage();
        setCurrentUsername(null);
        setCurrentPassword(null);
        setCurrentUser(null);
        CLI_Logic.IntroLogic();
    }


}






