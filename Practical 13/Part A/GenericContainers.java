public class GenericContainers
{
    // Generic Box class with get and set
    static class Box<T>
    {
        private T value;

        public void set(T value)
        {
            this.value = value;
        }

        public T get()
        {
            return value;
        }
    }

    // Generic Pair class
    static class Pair<K, V>
    {
        private K key;
        private V value;

        public Pair(K key, V value)
        {
            this.key = key;
            this.value = value;
        }

        public K getKey()
        {
            return key;
        }

        public V getValue()
        {
            return value;
        }
    }

    // Generic method to return first element of any array
    public static <T> T getFirst(T[] array)
    {
        if(array == null || array.length == 0)
        {
            return null;
        }
        return array[0];
    }

    public static void main(String[] args)
    {
        // Demonstration of Box with type safety without casts
        Box<String> stringBox = new Box<>();
        stringBox.set("Hello");
        String str = stringBox.get();
        System.out.println("Box Value: " + str);

        // Demonstration of Pair with different types
        Pair<Integer, String> pair = new Pair<>(101, "Java");
        Integer id = pair.getKey();
        String name = pair.getValue();
        System.out.println("Pair: " + id + " -> " + name);

        // Demonstration of generic method
        Integer[] numbers = {10, 20, 30};
        String[] words = {"Apple", "Banana", "Cherry"};

        System.out.println("First Number: " + getFirst(numbers));
        System.out.println("First Word: " + getFirst(words));
    }
}