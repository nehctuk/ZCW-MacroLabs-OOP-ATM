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
        User user = new User(username, password);
        UserFactory.addNewUser(user);

        String answer = "Your Username is: "
                +username+"\nYour Password is: "
                +password +"\n\nPlease don't forget these!\n";
        System.out.println(answer);
    }

    public static void getLoginDetails() {
        String username = CLI_Interface.getStringInput("Please enter your username.");
        String password = CLI_Interface.getStringInput("Please enter your password.");
        AtmLogic.setCurrentUsername(username);
        AtmLogic.setCurrentPassword(password);
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
        AtmLogic.addAccountToUser(accountType, balance);
        System.out.println("Your account has been completed, you will be redirected to the main menu.\n");
        CLI_Interface.printIntroMenu();
    }

    public static void exitMessage() {
        System.out.println("Thank you for doing business with us, have a great day.\n");
    }

    protected static void checkBalance() {
        Account account = CLI_Interface.getAccountAttempt();
        double accountBalance = account.getBalance();
        System.out.println("Your current balance is: "+String.format("$%,.2f",accountBalance)+"\n");
    }

    private static Account getAccountAttempt() {

        System.out.println(AtmLogic.getCurrentUser().getAccounts());
        int accountSelecton = Integer.parseInt(CLI_Interface.getStringInput("Please choose a valid account number."));
        Account account = AtmLogic.getCurrentUser().getSpecificAccount(accountSelecton);


        if (account == null) {
            System.err.println("Not a valid account number. Please return a valid account number.");
            account = CLI_Interface.getAccountAttempt();
        }

        return account;
    }

    protected static void transferWithinAccounts() {

        if (AtmLogic.getCurrentUser().getNumberOfAccounts() <= 1) {
            System.out.println("Insufficient number of accounts.");
        }

        else {
            System.out.println("This is the account you will transfer from.");
            Account accountFrom = CLI_Interface.getAccountAttempt();

            System.out.println("This is the account you will transfer into.");
            Account accountTo = CLI_Interface.getAccountAttempt();

            while (accountFrom == accountTo) {
                System.out.println("\nThe account selected is the same as the the account you would like to transfer from.");
                System.out.println("Please select a different transfer to account.");
                accountTo = CLI_Interface.getAccountAttempt();
            }


            double transferAmount = Double.parseDouble(
                    CLI_Interface.getStringInput("Enter an amount you would like to transfer." ));

            transferAmount = validAmount(accountFrom, transferAmount, "transfer");

            accountFrom = CLI_Interface.validWithdraw(accountFrom, transferAmount, "transfer");
            accountTo = CLI_Interface.validDeposit(accountTo, transferAmount, "transfer");

            System.out.println("Transfer complete.\n");
            System.out.println("Your "+accountFrom.getAccountType() +" balance is: "+ String.format("$%,.2f",accountFrom.getBalance()));
            System.out.println("Your "+accountTo.getAccountType() +" balance is: "+ String.format("$%,.2f",accountTo.getBalance()));
        }
    }

    public static double validAmount(Account account, double amount, String withdrawType) {
        double accountBalance = account.getBalance();
        while (amount <= 0 || amount > accountBalance) {
            if (amount <= 0) {
                System.out.println("Please enter an amount greater than 0.\n");
            }
            else if (amount > accountBalance) {
                System.out.println("Insufficient funds, please enter a lower amount.\n");
            }

            amount = Double.parseDouble(CLI_Interface.getStringInput("Enter an amount you would like to "+withdrawType+"."));
        }
        return amount;
    }

    public static Account validWithdraw(Account account, double amount, String withdrawType) {
        double accountBalance = account.getBalance();
        double returnAmount = validAmount(account, amount, withdrawType);
        account.setBalance(accountBalance-returnAmount);
        return account;
    }

    protected static void closeAccount() {
        Account account = CLI_Interface.getAccountAttempt();
        if (account.getBalance() > 0) {
            double input = Double.parseDouble(CLI_Interface.getStringInput(
            "In order to close the account you must transfer or withdraw all funds\n"
            +"1: Transfer\n"
            +"2: Withdraw"));
            if (input == 1) {
                CLI_Interface.transferWithinAccounts();
            }
            else if (input == 2) {
                CLI_Interface.withDrawAttempt("withdraw");
            }

        }
    }


    public static Account withDrawAttempt(String withdrawType) {

        Account account = CLI_Interface.getAccountAttempt();
        double accountBalance = account.getBalance();
        System.out.println("Your current balance is: "+String.format("$%,.2f",accountBalance));

        double withdrawAmount = Double.parseDouble(
                CLI_Interface.getStringInput("Enter an amount you would like to "+withdrawType+"." ));

        account = CLI_Interface.validWithdraw(account, withdrawAmount, withdrawType);

        System.out.println("Your new balance is: "+String.format("$%,.2f",account.getBalance())+"\n");
        return account;
    }

    public static Account validDeposit(Account account, double amount, String depositType) {
        double accountBalance = account.getBalance();

        while (amount <= 0) {
            if (amount <= 0) {
                System.out.println("Please enter an amount greater than 0.\n");
            }
            amount = Double.parseDouble(CLI_Interface.getStringInput("Enter an amount you would like to "+depositType+"."));
        }

        account.setBalance(accountBalance+amount);
        return account;
    }

    public static Account depositAttempt(String depositType) {

        Account account = CLI_Interface.getAccountAttempt();
        double accountBalance = account.getBalance();
        System.out.println("Your current balance is: "+String.format("$%,.2f",accountBalance)+"\n");

        double depositAmount = Double.parseDouble(
                CLI_Interface.getStringInput("Enter an amount you would like to "+depositType+"."));

        CLI_Interface.validDeposit(account, depositAmount, "deposit");

        System.out.println("Your new balance is: "+String.format("$%,.2f",account.getBalance())+"\n");
        return account;
    }




}


