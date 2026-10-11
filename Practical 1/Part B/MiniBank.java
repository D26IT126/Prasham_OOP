import java.util.Scanner;

record BankInfo(String name, String branch) {}

enum MenuOption
{
    OPEN_ACCOUNT, DEPOSIT, WITHDRAW, TRANSFER, EXIT
}
public class MiniBank
{
    public static void main(String args[])
    {
        BankInfo bank=new BankInfo("abc", "def");
        System.out.println("Bank Name:"+bank.name());
        System.out.println("Bank Branch:"+bank.branch());

        System.out.println("Welcome to the MiniBank CLI");

        Scanner sc=new Scanner(System.in);

        menuLoop:
        while(true)
        {
            System.out.println
            ("""
            Please select an option from the menu below:
            1. Open Account
            2. Deposit Cash
            3. Withdraw Cash
            4. Transfer Money
            5. Exit
            """);

            System.out.print("Your Choice:");
            String input=sc.next();
            MenuOption option=switch(input)
            {
                case "1" -> MenuOption.OPEN_ACCOUNT;
                case "Open" -> MenuOption.OPEN_ACCOUNT;

                case "2" -> MenuOption.DEPOSIT;
                case "Deposit" -> MenuOption.DEPOSIT;

                case "3" -> MenuOption.WITHDRAW;
                case "Withdraw" -> MenuOption.WITHDRAW;

                case "4" -> MenuOption.TRANSFER;
                case "Transfer" -> MenuOption.TRANSFER;

                case "5" -> MenuOption.EXIT;
                case "Exit" -> MenuOption.EXIT;

                default -> MenuOption.EXIT;
            };

            switch(option)
            {
                case OPEN_ACCOUNT -> System.out.println("Open Account - to be implemented later.");

                case DEPOSIT -> System.out.println("Deposit - to be implemented later.");

                case WITHDRAW -> System.out.println("Withdraw - to be implemented later.");

                case TRANSFER -> System.out.println("Transfer - to be implemented later.");

                case EXIT -> {
                    System.out.println("Thank you for using the MiniBank CLI. Goodbye!");
                    break menuLoop;
                }

            }
        }

        sc.close();
    }
}