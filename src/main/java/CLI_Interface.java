
public class CLI_Interface {
    public static String isNewUser;

    public static String welcomeMessage() {
        String response = "Welcome to the ATM!\n" +
                "Are you new User?";
        System.out.println(response);
        return response;
    }

    public static String printIntroMenu(String answer) {
        String existingUserMenu =
                        "Please select an option from the menu below!\n"+
                        "0: Withdraw\n"+
                        "1: Deposit\n"+
                        "2: Check Account Balance\n"+
                        "3: Create New Account\n"+
                        "4: Delete Existing Account\n"+
                        "5: Logout";

        if ("No".equalsIgnoreCase(answer)) {
            System.out.println(existingUserMenu);
            return existingUserMenu;
        }
        else if ("Yes".equalsIgnoreCase(answer)){
            String response = "Would you like to to create an account?";
            System.out.println(response);
            return response;
        }
        else {
            String response = "Please return 'Yes' or 'No' not: "+answer;
            System.out.println(response);
            return response;
        }
    }
}



