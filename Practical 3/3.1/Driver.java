public class Driver
{
    public static void main(String args[])
    {
        int count=0;
        Point points[]=
        {
            new Point(1, 2),
            new Point(3, 4),
            new Point(1, 2),
            new Point(5, 6),
            new Point(3, 4)
        };

        for(int i=0;i<points.length;i++)
        {
            boolean found= false;

            for(int j=0;j<i;j++)
            {
                if(points[i].equals(points[j]))
                {
                    found=true;
                    break;
                }
            }

            if(!found)
            {
                count++;
            }
        }

        System.out.println("District: "+count);
    }
}