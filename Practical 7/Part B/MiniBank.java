public class MiniBank
{
    public static void main(String[] args)
    {
        // Create an account with a negative balance
        Account account=new Account("Rahul",-500);

        // Display account details
        System.out.println(account);

        // Validate the account using reflection
        String[] errors=AnnotationValidator.validate(account);

        // Display validation results
        if(errors.length==0)
        {
            System.out.println("Account is valid.");
        }
        else
        {
            System.out.println("Validation Errors:");

            for(String error:errors)
            {
                System.out.println(error);
            }
        }
    }
}