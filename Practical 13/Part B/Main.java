import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Custom @Id annotation to mark the primary key field
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Id
{
}

// Sample Account entity using @Id annotation
class Account
{
    @Id
    private String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance)
    {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public double getBalance()
    {
        return balance;
    }

    @Override
    public String toString()
    {
        return "Account{" + "accountNumber='" + accountNumber + '\'' + ", balance=" + balance + '}';
    }
}

// Generic Repository using reflection to read @Id marked fields
class Repository<T>
{
    private Map<String, T> storage = new HashMap<>();

    // Saves an entity using reflection to find its @Id value
    public T save(T entity)
    {
        if(entity == null)
        {
            return null;
        }

        String idValue = null;
        Class<?> clazz = entity.getClass();

        // Search for field annotated with @Id
        for(Field field : clazz.getDeclaredFields())
        {
            if(field.isAnnotationPresent(Id.class))
            {
                field.setAccessible(true);
                try
                {
                    Object value = field.get(entity);
                    if(value != null)
                    {
                        idValue = value.toString();
                    }
                }
                catch(IllegalAccessException e)
                {
                    e.printStackTrace();
                }
                break;
            }
        }

        if(idValue != null)
        {
            storage.put(idValue, entity);
            return entity;
        }

        throw new IllegalArgumentException("No field annotated with @Id found or ID value is null.");
    }

    public T findById(String id)
    {
        return storage.get(id);
    }

    public ArrayList<T> findAll()
    {
        return new ArrayList<>(storage.values());
    }

    public boolean delete(String id)
    {
        return storage.remove(id) != null;
    }

    // Varargs method to save multiple objects at once
    @SafeVarargs
    public final void saveAll(T... items)
    {
        for(T item : items)
        {
            save(item);
        }
    }

    // Generic method with bounded type parameter
    public static <N extends Number> double average(N[] values)
    {
        if(values == null || values.length == 0)
        {
            return 0.0;
        }

        double sum = 0.0;
        for(N val : values)
        {
            sum += val.doubleValue();
        }

        return sum / values.length;
    }
}

public class Main
{
    public static void main(String[] args)
    {
        Repository<Account> accountRepo = new Repository<>();

        Account acc1 = new Account("ACC101", 2500.00);
        Account acc2 = new Account("ACC102", 1800.50);
        Account acc3 = new Account("ACC103", 4200.75);

        // Save multiple accounts using saveAll
        accountRepo.saveAll(acc1, acc2, acc3);

        // Fetch one account by ID
        Account fetchedAccount = accountRepo.findById("ACC102");
        System.out.println("Fetched Account: " + fetchedAccount);

        // Print all accounts
        System.out.println("\nAll Accounts in Repository:");
        for(Account acc : accountRepo.findAll())
        {
            System.out.println(acc);
        }

        // Testing bounded average method
        Double[] balances = {2500.00, 1800.50, 4200.75};
        System.out.println("\nAverage Balance: " + Repository.average(balances));
    }
}