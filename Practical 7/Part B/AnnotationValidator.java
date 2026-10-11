import java.lang.reflect.Field;
import java.util.ArrayList;
import model.annotation.*;

// Validate object fields using custom annotations
public class AnnotationValidator
{
    public static String[] validate(Object obj)
    {
        ArrayList<String> errors=new ArrayList<>();

        // Get all declared fields of the object
        Field[] fields=obj.getClass().getDeclaredFields();

        for(Field field:fields)
        {
            try
            {
                // Allow access to private fields
                field.setAccessible(true);
                Object value=field.get(obj);

                // Check fields annotated with @Positive
                if(field.isAnnotationPresent(Positive.class))
                {
                    Positive annotation=field.getAnnotation(Positive.class);

                    if(value instanceof Number && ((Number)value).doubleValue()<=0)
                    {
                        errors.add(field.getName()+" "+annotation.message());
                    }
                }

                // Check fields annotated with @MaxLength
                if(field.isAnnotationPresent(MaxLength.class))
                {
                    MaxLength annotation=field.getAnnotation(MaxLength.class);

                    if(value instanceof String && ((String)value).length()>annotation.value())
                    {
                        errors.add(field.getName()+" exceeds maximum length of "+annotation.value());
                    }
                }
            }
            catch(IllegalAccessException e)
            {
                errors.add("Cannot access field: "+field.getName());
            }
        }

        // Convert the list of errors into a String array
        return errors.toArray(new String[0]);
    }
}