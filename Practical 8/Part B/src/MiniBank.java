import exception.*;

// Main class for MiniBank Practical 8
public class MiniBank
{
    public static void main(String[] args)
    {
        // Create two bank accounts
        Account account1=new Account("Rahul",5000);
        Account account2=new Account("Amit",2000);

        System.out.println("Initial Account Details:");
        System.out.println(account1);
        System.out.println(account2);

        // Demonstrate a valid deposit
        try
        {
            account1.deposit(1000);
            System.out.println("\nDeposit successful.");
        }
        catch(InvalidAmountException e)
        {
            System.out.println("Deposit failed: "+e.getMessage());
        }

        // Demonstrate an invalid deposit
        try
        {
            account1.deposit(-500);
        }
        catch(InvalidAmountException e)
        {
            System.out.println("\nDeposit failed: "+e.getMessage());
        }

        // Demonstrate insufficient funds
        try
        {
            account1.withdraw(10000);
            System.out.println("Withdrawal successful.");
        }
        catch(InsufficientFundsException e)
        {
            System.out.println("\nWithdrawal failed: "+e.getMessage());
            System.out.println("Additional amount required: Rs. "+e.getShortfall());
        }
        catch(InvalidAmountException e)
        {
            System.out.println("Withdrawal failed: "+e.getMessage());
        }
        finally
        {
            System.out.println("Withdrawal operation completed.");
        }

        // Demonstrate a successful transfer
        try
        {
            System.out.println("\nTransferring Rs. 1500...");
            account1.transfer(account2,1500);
        }
        catch(BankException e)
        {
            System.out.println("Transfer failed: "+e.getMessage());
        }

        // Demonstrate a failed transfer
        try
        {
            System.out.println("\nTransferring Rs. 20000...");
            account1.transfer(account2,20000);
        }
        catch(BankException e)
        {
            System.out.println("Transfer failed: "+e.getMessage());
        }

        // Demonstrate account not found
        try
        {
            System.out.println("\nTransferring to an unknown account...");
            account1.transfer(null,500);
        }
        catch(AccountNotFoundException e)
        {
            System.out.println("Transfer failed: "+e.getMessage());
        }
        catch(BankException e)
        {
            System.out.println("Transfer failed: "+e.getMessage());
        }

        // Demonstrate try-with-resources
        System.out.println("\nTry-with-resources demonstration:");

        try(BankResource resource=new BankResource())
        {
            resource.showMessage();
        }

        // Display final account balances
        System.out.println("\nFinal Account Details:");
        System.out.println(account1);
        System.out.println(account2);
    }
}