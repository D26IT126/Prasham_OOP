import java.util.regex.Pattern;

// Validate banking-related user information
class Validator
{
    private static final Pattern MOBILE=Pattern.compile("^[6-9][0-9]{9}$");
    private static final Pattern EMAIL=Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern PAN=Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]$");
    private static final Pattern IFSC=Pattern.compile("^[A-Z]{4}0[A-Z0-9]{6}$");

    public static boolean isValidMobile(String mobile)
    {
        return mobile!=null && MOBILE.matcher(mobile).matches();
    }

    public static boolean isValidEmail(String email)
    {
        return email!=null && EMAIL.matcher(email).matches();
    }

    public static boolean isValidPan(String pan)
    {
        return pan!=null && PAN.matcher(pan).matches();
    }

    public static boolean isValidIfsc(String ifsc)
    {
        return ifsc!=null && IFSC.matcher(ifsc).matches();
    }
}

// Types of banking transactions
enum TransactionType
{
    DEPOSIT, WITHDRAW, TRANSFER
}

// Store the details of a banking command
record Command(TransactionType type, String accountNumber, long amount)
{
}

// Convert user input into a Command object
class CommandParser
{
    public static Command parse(String line)
    {
        if(line==null)
        {
            throw new IllegalArgumentException("Command cannot be null");
        }

        String[] parts=line.trim().split("\\s+");

        if(parts.length!=3)
        {
            throw new IllegalArgumentException("Invalid command format");
        }

        // Convert the command text into its required data types
        TransactionType type=TransactionType.valueOf(parts[0].toUpperCase());
        String accountNumber=parts[1];
        long amount=Long.parseLong(parts[2]);

        if(!accountNumber.matches("AC[0-9]{4,}") || amount<=0)
        {
            throw new IllegalArgumentException("Invalid account number or amount");
        }

        return new Command(type,accountNumber,amount);
    }
}

// Minimal account class for statement generation
class Account
{
    private String accountNumber;
    private String ownerName;
    private long balance;

    public Account(String accountNumber, String ownerName, long balance)
    {
        this.accountNumber=accountNumber;
        this.ownerName=ownerName;
        this.balance=balance;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public String getOwnerName()
    {
        return ownerName;
    }

    public long getBalance()
    {
        return balance;
    }
}

// Generate a multi-line account statement
class StatementFormatter
{
    public static String buildStatement(Account account)
    {
        StringBuilder sb=new StringBuilder();

        sb.append("----- MiniBank Statement -----\n");
        sb.append("Account Number: ").append(account.getAccountNumber()).append("\n");
        sb.append("Owner Name: ").append(account.getOwnerName()).append("\n");
        sb.append("Balance: Rs. ").append(account.getBalance()).append("\n");
        sb.append("------------------------------");

        return sb.toString();
    }
}

public class MiniBank
{
    public static void main(String[] args)
    {
        // Test mobile number validation
        System.out.println("Valid Mobile: "+Validator.isValidMobile("9876543210"));
        System.out.println("Invalid Mobile: "+Validator.isValidMobile("12345"));

        // Test email validation
        System.out.println("Valid Email: "+Validator.isValidEmail("rahul@gmail.com"));
        System.out.println("Invalid Email: "+Validator.isValidEmail("rahulgmail.com"));

        // Test PAN validation
        System.out.println("Valid PAN: "+Validator.isValidPan("ABCDE1234F"));
        System.out.println("Invalid PAN: "+Validator.isValidPan("ABC123"));

        // Test IFSC validation
        System.out.println("Valid IFSC: "+Validator.isValidIfsc("SBIN0001234"));
        System.out.println("Invalid IFSC: "+Validator.isValidIfsc("SBI123"));

        // Parse a sample banking command
        Command command=CommandParser.parse("DEPOSIT AC0001 500");

        System.out.println("\nTransaction Type: "+command.type());
        System.out.println("Account Number: "+command.accountNumber());
        System.out.println("Amount: "+command.amount());

        // Generate an account statement
        Account account=new Account("AC0001","Rahul",5000);

        System.out.println();
        System.out.println(StatementFormatter.buildStatement(account));
    }
}