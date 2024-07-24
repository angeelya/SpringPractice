package angeelya.spring.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class NameContentValidator implements ConstraintValidator<NameContent,String> {
    @Override
    public void initialize(NameContent constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return name!=null&&name.matches("^[A-z]+$");
    }
}
