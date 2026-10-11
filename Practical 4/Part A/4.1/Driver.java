public class Driver
{

    public static void main(String[] args)
    {
        String[] testPasswords =
        {
            "abc",          // Should be Weak (0 rules passed)
            "password",     // Should be Weak (1 rule passed: length)
            "Password",     // Should be Medium (2 rules passed: length, uppercase)
            "Password123",  // Should be Medium (3 rules passed: length, uppercase, digit)
            "Abcd1234!"     // Should be Strong (4 rules passed)
        };

        for (String pw : testPasswords)
        {
            System.out.println("Testing Password: \"" + pw + "\"");
            
            PasswordChecker checker = new PasswordChecker(pw);

            boolean hasLength = checker.checkLength();
            boolean hasUpper = checker.checkUppercase();
            boolean hasDigit = checker.checkDigit();
            boolean hasSpecial = checker.checkSpecial();

            System.out.println("  - Length >= 8: " + hasLength);
            System.out.println("  - Uppercase:   " + hasUpper);
            System.out.println("  - Digit:       " + hasDigit);
            System.out.println("  - Special Char: " + hasSpecial);
            
            String label = checker.strength();
            System.out.println("Result: " + pw + " -> " + label);
            System.out.println("----------------------------------------");
        }
    }
}