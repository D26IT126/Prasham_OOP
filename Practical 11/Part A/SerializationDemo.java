import java.io.*;

class Student implements Serializable
{
    private static final long serialVersionUID=1L;

    int id;
    String name;
    transient String password;

    Student(int id, String name, String password)
    {
        this.id=id;
        this.name=name;
        this.password=password;
    }

    void display()
    {
        System.out.println("ID: "+id+", Name: "+name+", Password: "+password);
    }
}

public class SerializationDemo
{
    public static void main(String[] args)
    {
        Student[] students={
            new Student(1, "Rahul", "pass123"),
            new Student(2, "Amit", "pass456"),
            new Student(3, "Priya", "pass789")
        };

        // Serialize the array of Student objects
        try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("students.dat")))
        {
            out.writeObject(students);
            System.out.println("Objects saved successfully.");
        }
        catch(IOException e)
        {
            System.out.println("Error saving objects: "+e.getMessage());
            return;
        }

        // Deserialize the array into new objects
        Student[] loadedStudents;

        try(ObjectInputStream in=new ObjectInputStream(new FileInputStream("students.dat")))
        {
            loadedStudents=(Student[])in.readObject();
            System.out.println("Objects loaded successfully.");
        }
        catch(IOException | ClassNotFoundException e)
        {
            System.out.println("Error loading objects: "+e.getMessage());
            return;
        }

        System.out.println("\nOriginal Objects:");
        for(Student s:students)
        {
            s.display();
        }

        System.out.println("\nDeserialized Objects:");
        for(Student s:loadedStudents)
        {
            s.display();
        }

        // Confirm that deserialized objects are new instances
        System.out.println("\nSame object reference: "+(students[0]==loadedStudents[0]));
    }
}