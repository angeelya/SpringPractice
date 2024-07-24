package angeelya.spring.validation;

import angeelya.spring.database.model.Discipline;
import angeelya.spring.database.repository.DisciplineRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UniqueDisciplineValidator implements ConstraintValidator<DisciplineUnique,String> {
    @Autowired
    private DisciplineRepository disciplineRepository;
    @Override
    public void initialize(DisciplineUnique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Discipline> discipline = disciplineRepository.findDisciplineByDisciplineName(name);
        return discipline.isEmpty();
    }
}
