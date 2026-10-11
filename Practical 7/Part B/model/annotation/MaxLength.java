package model.annotation;

import java.lang.annotation.*;

// Annotation to restrict the maximum length of a String field
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxLength
{
    int value();
}