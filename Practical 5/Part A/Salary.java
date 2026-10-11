abstract class Employee
{
    String name;
    int id;

    Employee(String name, int id)
    {
        this.name = name;
        this.id = id;
    }

    abstract double monthlySalary();
}

class FullTime extends Employee
{
    double salary;

    FullTime(String name, int id, double salary)
    {
        super(name, id);
        this.salary = salary;
    }

    double monthlySalary()
    {
        return salary;
    }
}

class PartTime extends Employee
{
    int hours;
    double rate;

    PartTime(String name, int id, int hours, double rate) {
        super(name, id);
        this.hours = hours;
        this.rate = rate;
    }

    double monthlySalary() {
        return hours * rate;
    }
}

class Intern extends Employee
{
    double stipend;

    Intern(String name, int id, double stipend) {
        super(name, id);
        this.stipend = stipend;
    }

    double monthlySalary() {
        return stipend;
    }
}

public class Salary
{
    public static void main(String[] args)
    {
        Employee[] employees=
        {
            new FullTime("Amit", 101, 50000),
            new PartTime("Raj", 102, 100, 200),
            new Intern("Priya", 103, 10000),
            new FullTime("Neha", 104, 45000),
            new Intern("Karan", 105, 8000)
        };

        double total = 0;

        for (Employee employee : employees)
        {
            double salary = employee.monthlySalary();

            System.out.print(employee.name + " (" + employee.id + "): " + salary);

            if (employee instanceof Intern)
            {
                System.out.print(" - Intern");
            }

            System.out.println();

            total += salary;
        }

        System.out.println("Total Salary: " + total);
    }
}