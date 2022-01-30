package com.app.zlobek.util.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Constraint(validatedBy = DateRangeValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateRange {
    String message() default "Data początkowa jest późniejsza niż data końcowa";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
