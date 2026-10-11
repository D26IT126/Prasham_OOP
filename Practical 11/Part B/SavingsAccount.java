// Savings account supporting serialization
public class SavingsAccount extends Account
{
    private static final long serialVersionUID=1L;

    private double interestRate;

    public SavingsAccount(String accountNumber,Customer customer,long balance,double interestRate)
    {
        super(accountNumber,customer,balance);
        this.interestRate=interestRate;
    }

    public double getInterestRate()
    {
        return interestRate;
    }

    @Override
    public String toString()
    {
        return super.toString()+", Interest Rate: "+interestRate+"%";
    }
}