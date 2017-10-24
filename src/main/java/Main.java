import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        CLI_Interface.welcomeMessage();

        Scanner sc = new Scanner(System.in);
        CLI_Interface.printIntroMenu(sc.nextLine());

        if ("Yes".equalsIgnoreCase(sc.nextLine())){
            System.out.println("Please enter account type.");
            String type = sc.nextLine();
            System.out.println("Please enter balance.");
            double balance = sc.nextDouble();
            Account account = new Account(type, balance);
            System.out.println(account.toString());
        }



        
    }
}

