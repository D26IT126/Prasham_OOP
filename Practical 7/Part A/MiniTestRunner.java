import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

public class MiniTestRunner
{
    // Custom annotation to mark methods for execution
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Run
    {
    }

    // Sample class containing test methods
    static class SampleTests
    {
        @Run
        public void testOne()
        {
            System.out.println("Executed: testOne");
        }

        public void helperMethod()
        {
            System.out.println("Skipped: helperMethod (no @Run annotation)");
        }

        @Run
        public void testTwo()
        {
            System.out.println("Executed: testTwo");
        }
    }

    public static void main(String[] args)
    {
        int executedCount = 0;
        Class<SampleTests> clazz = SampleTests.class;

        try
        {
            // Instantiate the target class
            SampleTests instance = clazz.getDeclaredConstructor().newInstance();

            // Inspect all declared methods using reflection
            for(Method method : clazz.getDeclaredMethods())
            {
                // Check if the method is annotated with @Run
                if(method.isAnnotationPresent(Run.class))
                {
                    // Invoke the no-arg method
                    method.invoke(instance);
                    executedCount++;
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        // Report total number of @Run methods executed
        System.out.println("Total @Run methods executed: " + executedCount);
    }
}