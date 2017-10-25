public class AtmLogic {
    private static String currentUsername;
    private static String currentPassword;
    private static User currentUser;

    public static void setCurrentUser(User currentUser) { AtmLogic.currentUser = currentUser; }
    public static User getCurrentUser() { return currentUser; }
    public static String getCurrentUsername() { return AtmLogic.currentUsername; }
    public static void setCurrentUsername(String username) { AtmLogic.currentUsername = username; }
    public static String getCurrentPassword() { return AtmLogic.currentPassword; }
    public static void setCurrentPassword(String password) { AtmLogic.currentPassword = password; }










    public static void IntroLogic() {
        String welcomeMessage = CLI_Interface.welcomeMessage();
        if ("Yes".equalsIgnoreCase(welcomeMessage)) {
            CLI_Interface.createNewUser();
            CLI_Interface.createNewBankAccount();
            AtmLogic.menuSelection();
        }
        else if ("No".equalsIgnoreCase(welcomeMessage)) {
            AtmLogic.login();
            AtmLogic.menuSelection();
        }
        else {
            CLI_Interface.InvalidResponse(welcomeMessage);
            AtmLogic.IntroLogic();
            }
        }

    private static void login() {
        CLI_Interface.getLoginDetails();
        String currentUser = AtmLogic.getCurrentUsername();
        String currentPassword = AtmLogic.getCurrentPassword();
        boolean accountFound = false;

        for (User user: UserWarehouse.getUsers()) {
            if (currentUser.equalsIgnoreCase(user.getUsername()) &&
                    currentPassword.equals(user.getPassword())) {
                AtmLogic.setCurrentUser(user);
                CLI_Interface.printIntroMenu();
                accountFound = true;
            }

        }
        if (!accountFound){
            String response = CLI_Interface.loginFalure();

            if ("Yes".equalsIgnoreCase(response)) {
                AtmLogic.login();
            }
            else if ("No".equalsIgnoreCase(response)) {
                AtmLogic.IntroLogic();
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
            CLI_Interface.withDrawAttempt("withdraw");
            CLI_Interface.printIntroMenu();
            AtmLogic.menuSelection();
        }
        else if (selection == 1) {
            CLI_Interface.depositAttempt("deposit");
            CLI_Interface.printIntroMenu();
            AtmLogic.menuSelection();
        }
        else if (selection == 2) {
            CLI_Interface.transferWithinAccounts();
            CLI_Interface.printIntroMenu();
            AtmLogic.menuSelection();
        }
        else if (selection == 3) {
            CLI_Interface.createNewBankAccount();
            AtmLogic.menuSelection();
        }
        else if (selection == 4) {



        }
        else if (selection == 6) {
            CLI_Interface.checkBalance();
            CLI_Interface.printIntroMenu();
            AtmLogic.menuSelection();
        }
        else if (selection == 7) {
            AtmLogic.logout();
        }
    }

    public static void logout() {
        CLI_Interface.exitMessage();
        setCurrentUsername(null);
        setCurrentPassword(null);
        setCurrentUser(null);
        AtmLogic.IntroLogic();
    }


}






