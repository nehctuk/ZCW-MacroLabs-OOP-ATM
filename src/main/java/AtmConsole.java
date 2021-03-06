import java.util.Scanner;

public class AtmConsole {
    private String currentUsername;
    private String currentPassword;

    public static String getStringInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static String getStringInput(String input) {
        Scanner sc = new Scanner(System.in);
        System.out.println(input);
        return sc.nextLine();
    }

    public static String welcomeMessage() {
        String response = AtmConsole.getStringInput(
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

        String username = AtmConsole.getStringInput("Please enter a username.");
        String password = AtmConsole.getStringInput("Please enter a password.");
        AtmLogic.createAndAddUser(username, password);
        String answer = "Your Username is: "
                +username+"\nYour Password is: "
                +password +"\n\nPlease don't forget these!\n";
        System.out.println(answer);
    }

    public static void getLoginDetails() {
        String username = AtmConsole.getStringInput("Please enter your username.");
        String password = AtmConsole.getStringInput("Please enter your password.");
        AtmLogic.setCurrentUsername(username);
        AtmLogic.setCurrentPassword(password);
    }

    public static String loginFalure() {
        System.out.println("Username and password incorrect.");
        String input = AtmConsole.getStringInput("Would you like to try and login again?");

        return input;
    }

    public static void createNewBankAccount() {
        String accountType = AtmConsole.getStringInput("Please enter an account type to create.");
        double balance = Double.parseDouble(AtmConsole.getStringInput("Please enter the starting balance of your account."));
        AtmLogic.addAccountToUser(accountType, balance);
        System.out.println("Your account has been completed, you will be redirected to the main menu.\n");
        AtmConsole.printIntroMenu();
    }

    public static void exitMessage() {
        System.out.println("Thank you for doing business with us, have a great day.\n");
    }

    protected static void checkBalance() {
        Account account = AtmConsole.getAccountAttempt();
        double accountBalance = account.getBalance();
        System.out.println("Your current balance is: "+String.format("$%,.2f",accountBalance)+"\n");
    }

    protected static Account getAccountAttempt() {
        System.out.println(AtmLogic.getCurrentUser().getAccountsToString());
        int accountSelecton = Integer.parseInt(AtmConsole.getStringInput("Please choose a valid account number."));
        Account account = AtmLogic.getCurrentUser().getSpecificAccount(accountSelecton);

        if (account == null) {
            System.err.println("Not a valid account number. Please return a valid account number.");
            account = AtmConsole.getAccountAttempt();
        }

        return account;
    }

    protected static void transferWithinAccounts() {

        if (AtmLogic.getCurrentUser().getNumberOfAccounts() <= 1) {
            System.out.println("Insufficient number of accounts.");
        }

        else {
            System.out.println("This is the account you will transfer from.");
            Account accountFrom = AtmConsole.getAccountAttempt();

            System.out.println("This is the account you will transfer into.");
            Account accountTo = AtmConsole.getAccountAttempt();

            while (accountFrom == accountTo) {
                System.out.println("\nThe account selected is the same as the the account you would like to transfer from.");
                System.out.println("Please select a different transfer to account.");
                accountTo = AtmConsole.getAccountAttempt();
            }


            double transferAmount = Double.parseDouble(
                    AtmConsole.getStringInput("Enter an amount you would like to transfer." ));

            transferAmount = validAmount(accountFrom, transferAmount, "transfer");

            accountFrom = AtmConsole.validWithdraw(accountFrom, transferAmount, "transfer");
            accountTo = AtmConsole.validDeposit(accountTo, transferAmount, "transfer");

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

            amount = Double.parseDouble(AtmConsole.getStringInput("Enter an amount you would like to "+withdrawType+"."));
        }



        return amount;
    }

    public static Account validWithdraw(Account account, double amount, String withdrawType) {
        double accountBalance = account.getBalance();
        double returnAmount = validAmount(account, amount, withdrawType);
        account.setBalance(accountBalance-returnAmount);
        return account;
    }

    // Did not have to time to finish
//    protected static void closeAccount() {
//        Account account = AtmConsole.getAccountAttempt();
//        if (account.getBalance() > 0) {
//            double input = Double.parseDouble(AtmConsole.getStringInput(
//            "In order to close the account you must transfer or withdraw all funds\n"
//            +"1: Transfer\n"
//            +"2: Withdraw"));
//            if (input == 1) {
//                AtmConsole.transferWithinAccounts();
//            }
//            else if (input == 2) {
//                AtmConsole.withDrawAttempt("withdraw");
//            }
//
//        }
//    }


    public static Account withDrawAttempt(String withdrawType) {

        Account account = AtmConsole.getAccountAttempt();
        double accountBalance = account.getBalance();
        System.out.println("Your current balance is: "+String.format("$%,.2f",accountBalance));

        double withdrawAmount = Double.parseDouble(
                AtmConsole.getStringInput("Enter an amount you would like to "+withdrawType+"." ));

        account = AtmConsole.validWithdraw(account, withdrawAmount, withdrawType);

        System.out.println("Your new balance is: "+String.format("$%,.2f",account.getBalance())+"\n");
        return account;
    }

    public static Account validDeposit(Account account, double amount, String depositType) {
        double accountBalance = account.getBalance();

        while (amount <= 0) {
            if (amount <= 0) {
                System.out.println("Please enter an amount greater than 0.\n");
            }
            amount = Double.parseDouble(AtmConsole.getStringInput("Enter an amount you would like to "+depositType+"."));
        }

        account.setBalance(accountBalance+amount);
        return account;
    }

    public static Account depositAttempt(String depositType) {

        Account account = AtmConsole.getAccountAttempt();
        double accountBalance = account.getBalance();
        System.out.println("Your current balance is: "+String.format("$%,.2f",accountBalance)+"\n");

        double depositAmount = Double.parseDouble(
                AtmConsole.getStringInput("Enter an amount you would like to "+depositType+"."));

        AtmConsole.validDeposit(account, depositAmount, "deposit");

        System.out.println("Your new balance is: "+String.format("$%,.2f",account.getBalance())+"\n");
        return account;
    }




}


