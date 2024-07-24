package angeelya.spring.validation;

import angeelya.spring.model.Group;
import angeelya.spring.repository.GroupRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class UniqueGroupValidator implements ConstraintValidator<GroupUnique, String> {
    @Autowired
    private GroupRepository groupRepository;
    @Override
    public void initialize(GroupUnique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Group> group = groupRepository.findByGroupName(name);
        return group.isEmpty();
    }
}
