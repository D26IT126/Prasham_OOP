import model.*;
import util.WithdrawRule;
import static service.BankService.processWithdrawal;

public class MiniBank
{
    public static void main(String[] args)
    {
        // Create different types of accounts
        Account[] accounts=new Account[3];

        accounts[0]=new SavingsAccount("Rahul",10000,2000);
        accounts[1]=new CurrentAccount("Amit",5000,3000);
        accounts[2]=new FixedDepositAccount("Priya",20000);

        // Display account details using polymorphism
        for(Account account:accounts)
        {
            System.out.println("Account Type: "+account.getClass().getSimpleName());
            System.out.println("Owner: "+account.getOwnerName());
            System.out.println("Balance: Rs. "+account.getBalance());
            System.out.println("Interest Rate: "+account.interestRate()+"%");
            System.out.println("Yearly Interest: Rs. "+account.yearlyInterest());

            // Check whether the account is premium
            if(account instanceof Premium)
            {
                System.out.println("Premium Account: Yes");
            }

            System.out.println("-----------------------------");
        }

        // Implement WithdrawRule using an anonymous class
        WithdrawRule rule1=new WithdrawRule()
        {
            @Override
            public boolean allow(Account account, long amount)
            {
                return account.canWithdraw(amount);
            }
        };

        // Implement WithdrawRule using a lambda expression
        WithdrawRule rule2=(account,amount) -> account.canWithdraw(amount);

        // Test withdrawal using the anonymous class
        boolean result1=processWithdrawal(accounts[0],3000,rule1);

        System.out.println("Anonymous Class Withdrawal: "+result1);
        System.out.println("Savings Balance: Rs. "+accounts[0].getBalance());

        // Test withdrawal using the lambda expression
        boolean result2=processWithdrawal(accounts[1],6000,rule2);

        System.out.println("Lambda Withdrawal: "+result2);
        System.out.println("Current Balance: Rs. "+accounts[1].getBalance());
    }
}