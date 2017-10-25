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
        String welcomeMessage = AtmConsole.welcomeMessage();
        if ("Yes".equalsIgnoreCase(welcomeMessage)) {
            AtmConsole.createNewUser();
            AtmConsole.createNewBankAccount();
            AtmLogic.menuSelection();
        }
        else if ("No".equalsIgnoreCase(welcomeMessage)) {
            AtmLogic.login();
            AtmLogic.menuSelection();
        }
        else {
            AtmConsole.InvalidResponse(welcomeMessage);
            AtmLogic.IntroLogic();
            }
        }

    private static void login() {
        AtmConsole.getLoginDetails();
        String currentUser = AtmLogic.getCurrentUsername();
        String currentPassword = AtmLogic.getCurrentPassword();

        boolean accountFound = UserWarehouse.doesUserExist(currentUser,currentPassword);

        if (accountFound) {
            AtmConsole.printIntroMenu();
        }
        else {
            String response = AtmConsole.loginFalure();

            if ("Yes".equalsIgnoreCase(response)) {
                AtmLogic.login();
            }
            else if ("No".equalsIgnoreCase(response)) {
                AtmLogic.IntroLogic();
            }
            else {
                AtmConsole.InvalidResponse(response);
            }
        }
    }

    public static void addAccountToUser(String accountType, double balance) {
        currentUser.createAccount(accountType, balance);
    }



    public static void menuSelection() {

        int selection = Integer.parseInt(AtmConsole.getStringInput());

        switch (selection) {
            case 0:
                AtmConsole.withDrawAttempt("withdraw");
                AtmConsole.printIntroMenu();
                AtmLogic.menuSelection();
                break;
            case 1:
                AtmConsole.depositAttempt("deposit");
                AtmConsole.printIntroMenu();
                AtmLogic.menuSelection();
                break;
            case 2:
                AtmConsole.transferWithinAccounts();
                AtmConsole.printIntroMenu();
                AtmLogic.menuSelection();
                break;
            case 3:
                AtmConsole.createNewBankAccount();
                AtmLogic.menuSelection();
                break;
            case 4:

                break;
            case 5:

                break;
            case 6:
                AtmConsole.checkBalance();
                AtmConsole.printIntroMenu();
                AtmLogic.menuSelection();
                break;
            case 7:
                AtmLogic.logout();
                break;
            default:
                AtmConsole.InvalidResponse(""+selection+"\n");
                AtmConsole.printIntroMenu();
                AtmLogic.menuSelection();
                break;
        }

    }

    public static void logout() {
        AtmConsole.exitMessage();
        setCurrentUsername(null);
        setCurrentPassword(null);
        setCurrentUser(null);
        AtmLogic.IntroLogic();
    }


}






