public class PasswordChecker {

    private String pw;

    public PasswordChecker(String pw)
    {
        this.pw = pw;
    }

    public boolean checkLength()
    {
        return pw != null && pw.matches(".{8,}");
    }

    public boolean checkUppercase()
    {
        return pw != null && pw.matches(".*[A-Z].*");
    }

    public boolean checkDigit()
    {
        return pw != null && pw.matches(".*[0-9].*");
    }

    public boolean checkSpecial()
    {
        return pw != null && pw.matches(".*[^a-zA-Z0-9].*");
    }

    public String strength()
    {
        int passedRules= 0;

        if (checkLength())
        {
            passedRules++;
        }
        if (checkUppercase())
        {
            passedRules++;
        }
        if (checkDigit())
        {
            passedRules++;
        }
        if (checkSpecial())
        {
            passedRules++;
        }
        if (passedRules >= 4)
        {
            return "Strong";
        }
        else if(passedRules >= 2)
        {
            return "Medium";
        }
        else
        {
            return "Weak";
        }
    }
}