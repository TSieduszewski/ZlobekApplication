package com.app.zlobek.util.validation;

import com.app.zlobek.entity.Shift;
import com.app.zlobek.util.shift.ShiftWithBabysitter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateRangeValidator implements ConstraintValidator<DateRange, Shift> {

    @Override
    public boolean isValid(Shift shift, ConstraintValidatorContext constraintValidatorContext) {

        return (shift.getStartShift().isBefore(shift.getEndShift())&&shift.getStartShift().until(shift.getEndShift(), ChronoUnit.HOURS)<=10);
    }

    @Override
    public void initialize(DateRange constraintAnnotation) {

    }

}
