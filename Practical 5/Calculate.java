abstract class Shape
{
    abstract double area();
}

class Circle extends Shape
{
    public double area()
    {
        int radius=6;
        double area= Math.PI*Math.pow(radius,2);
        System.out.println("Area of circle: "+area+" unit(s)");
        return area;
    }
}

class Rectangle extends Shape
{
    public double area()
    {
        int length=10, breadth=12;
        double area= length*breadth;
        System.out.println("Area of Rectangle: "+area+" unit(s)");
        return area;
    }
}

class Triangle extends Shape
{
    public double area()
    {
        int base= 15, height=12;
        double area= (1/2)*(base*height);
        System.out.println("Area of triangle: "+area+" unit(s)");
        return area;
    }
}

public class Calculate
{
    public static void main(String args[])
    {
        double runningTotal= 0, largest=0;

        Shape S[]= 
        {
            new Circle(),
            new Rectangle(),
            new Triangle()
        };

        for(Shape area: S)
        {
            double n_area=area.area();
            runningTotal+=n_area;

            if(n_area>largest)
            {
                largest=n_area;
            }
        }

        System.out.println("Total Area: "+runningTotal+" unit(s)");
        System.out.println("Largest Area: "+largest+" unit(s)");
    }
}