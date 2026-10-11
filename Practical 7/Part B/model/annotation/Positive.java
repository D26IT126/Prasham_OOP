package model.annotation;

import java.lang.annotation.*;

// Annotation to ensure a numeric field has a positive value
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Positive
{
    String message() default "must be > 0";
}