import java.util.Scanner;

public class CLI_Interface {
    private String currentUsername;
    private String currentPassword;

    public static String welcomeMessage() {
        String response = CLI_Interface.getStringInput(
                "Welcome to the ATM!\n" +
                        "Are you new User?");
        return response;
    }

    public static void printIntroMenu() {
        String existingUserMenu =
                "Please select an option from the menu below!\n"+
                        "0: Withdraw\n"+
                        "1: Deposit\n"+
                        "2: Transfer\n" +
                        "3: Open New Account\n" +
                        "4: Close Existing Account\n"+
                        "5: Print Transaction History\n"+
                        "6: Check Account Balance\n"+
                        "7: Logout";
        System.out.println(existingUserMenu);
    }

    public static void InvalidResponse(String answer) {
        String response = "Please return a valid response not: "+answer;
        System.out.println(response);
    }

    public static void createNewUser() {

        String username = CLI_Interface.getStringInput("Please enter a username.");
        String password = CLI_Interface.getStringInput("Please enter a password.");
        UserFactory.addNewUser(username,password);
        String answer = "Your Username is: "
                +username+"\nYour Password is: "
                +password +"\n\nPlease don't forget these!\n";
        System.out.println(answer);
    }

    public static void getLoginDetails() {
        String username = CLI_Interface.getStringInput("Please enter your username.");
        String password = CLI_Interface.getStringInput("Please enter your password.");
        CLI_Logic.setCurrentUsername(username);
        CLI_Logic.setCurrentPassword(password);
    }

    public static String loginFalure() {
        System.out.println("Username and password incorrect.");
        String input = CLI_Interface.getStringInput("Would you like to try and login again?");

        return input;
    }

    public static String getStringInput(String input) {
        Scanner sc = new Scanner(System.in);
        System.out.println(input);
        return sc.nextLine();
    }

    public static String getStringInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static void createNewBankAccount() {
        String accountType = CLI_Interface.getStringInput("Please enter an account type to create.");
        double balance = Double.parseDouble(CLI_Interface.getStringInput("Please enter the starting balance of your account."));
        CLI_Logic.addAccountToUser(accountType, balance);
        System.out.println("Your account has been completed, you will be redirected to the main menu.\n");
        CLI_Interface.printIntroMenu();
    }

    public static void exitMessage() {
        System.out.println("Thank you for doing business with us, have a great day.\n");
    }


}


