public class BoundedStats
{
    // Generic method bounded to Number class and its subclasses
    public static <N extends Number> double average(N[] values)
    {
        if(values == null || values.length == 0)
        {
            return 0.0;
        }

        double sum = 0.0;
        for(N val : values)
        {
            // doubleValue() is available because N extends Number
            sum += val.doubleValue();
        }

        return sum / values.length;
    }

    public static void main(String[] args)
    {
        // Works for Integer[]
        Integer[] intArr = {10, 20, 30};
        System.out.println("Integer Average: " + average(intArr));

        // Works for Double[]
        Double[] doubleArr = {1.5, 2.5, 3.5};
        System.out.println("Double Average: " + average(doubleArr));

        // Works for Long[]
        Long[] longArr = {100L, 200L, 300L};
        System.out.println("Long Average: " + average(longArr));

        /*
         * Note on Non-Number Types:
         * Passing non-Number types like String[] will cause a compile-time error.
         * The type parameter boundary <N extends Number> restricts N to Number or its subclasses.
         * Non-number classes do not inherit from java.lang.Number, so they fail type validation 
         * and do not possess numeric methods like doubleValue().
         */
    }
}