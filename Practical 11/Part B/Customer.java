import java.io.Serializable;

// Customer class supporting serialization
public class Customer implements Serializable
{
    private static final long serialVersionUID=1L;

    private String name;
    private String email;

    public Customer(String name,String email)
    {
        this.name=name;
        this.email=email;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    @Override
    public String toString()
    {
        return "Name: "+name+", Email: "+email;
    }
}