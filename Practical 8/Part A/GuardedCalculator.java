import java.util.Scanner;

// Custom checked exception for division by zero
class DivideByZeroException extends Exception
{
    public DivideByZeroException(String message) {
        super(message);
    }
}

public class GuardedCalculator
{

    static double calculate(double a, double b, char op) throws DivideByZeroException
    {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) {
                    throw new DivideByZeroException("Cannot divide " + a + " by zero.");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Unknown operator '" + op + "'. Use + - * /");
        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        boolean success = false;
        int attempt = 0;

        while (!success)
        {
            attempt++;
            String status = "FAILED";
            try
            {
                System.out.print("Enter first number: ");
                double a = Double.parseDouble(sc.nextLine().trim());

                System.out.print("Enter operator (+, -, *, /): ");
                String opInput = sc.nextLine().trim();
                if (opInput.length() != 1) {
                    throw new IllegalArgumentException("Operator must be a single character: + - * /");
                }
                char op = opInput.charAt(0);

                System.out.print("Enter second number: ");
                double b = Double.parseDouble(sc.nextLine().trim());

                double result = calculate(a, b, op);
                System.out.println("Result: " + a + " " + op + " " + b + " = " + result);

                success = true;
                status = "SUCCESS";

            } 
            catch (NumberFormatException e)
            {
                System.out.println("Invalid number entered. Please type a valid numeric value.");
            }
            catch (DivideByZeroException e)
            {
                System.out.println("Math error: " + e.getMessage() + " Please try again.");
            }
            catch (IllegalArgumentException e) 
            {
                System.out.println("Invalid operator: " + e.getMessage());
            }
            finally
            {
                System.out.println("[LOG] Attempt #" + attempt + ": " + status);
                System.out.println();
            }
        }

        sc.close();
    }
}