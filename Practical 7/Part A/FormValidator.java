import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FormValidator
{
    // Annotation to check non-blank strings
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface NotBlank
    {
    }

    // Annotation to check maximum string length
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface MaxLength
    {
        int value();
    }

    // SignupForm model annotated with custom annotations
    static class SignupForm
    {
        @NotBlank
        @MaxLength(10)
        private String username;

        @NotBlank
        private String password;

        public SignupForm(String username, String password)
        {
            this.username = username;
            this.password = password;
        }
    }

    // Reflection-based validator function
    public static List<String> validate(Object obj)
    {
        List<String> errors = new ArrayList<>();
        Class<?> objClass = obj.getClass();

        for(Field field : objClass.getDeclaredFields())
        {
            field.setAccessible(true);
            try
            {
                Object value = field.get(obj);
                String fieldValue = (value != null) ? value.toString() : "";

                // Validate @NotBlank
                if(field.isAnnotationPresent(NotBlank.class))
                {
                    if(value == null || fieldValue.trim().isEmpty())
                    {
                        errors.add("Field '" + field.getName() + "' cannot be blank.");
                    }
                }

                // Validate @MaxLength
                if(field.isAnnotationPresent(MaxLength.class))
                {
                    MaxLength maxLength = field.getAnnotation(MaxLength.class);
                    if(fieldValue.length() > maxLength.value())
                    {
                        errors.add("Field '" + field.getName() + "' exceeds maximum length of " + maxLength.value() + ".");
                    }
                }
            }
            catch(IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
        return errors;
    }

    public static void main(String[] args)
    {
        // Testing invalid form
        SignupForm invalidForm = new SignupForm("SuperLongUsernameHere", "");
        List<String> errors = validate(invalidForm);

        System.out.println("Validation Errors:");
        for(String error : errors)
        {
            System.out.println("- " + error);
        }
    }
}