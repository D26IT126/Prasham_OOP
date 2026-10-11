// Current account supporting serialization
public class CurrentAccount extends Account
{
    private static final long serialVersionUID=1L;

    private long overdraftLimit;

    public CurrentAccount(String accountNumber,Customer customer,long balance,long overdraftLimit)
    {
        super(accountNumber,customer,balance);
        this.overdraftLimit=overdraftLimit;
    }

    public long getOverdraftLimit()
    {
        return overdraftLimit;
    }

    @Override
    public String toString()
    {
        return super.toString()+", Overdraft Limit: Rs. "+overdraftLimit;
    }
}