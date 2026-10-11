package model.annotation;

import java.lang.annotation.*;

// Marker annotation to identify an ID field
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Id
{
}