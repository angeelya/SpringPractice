package angeelya.spring.validation.service;


import angeelya.spring.exceptionHandler.exception.ValidationErrorsException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;
@Service
public class ValidationService {
    public void validation(BindingResult bindingResult) throws ValidationErrorsException {
        if (bindingResult.hasErrors()) {
            throw new ValidationErrorsException(getValidationErrors(bindingResult));
        }
    }
    private   String getValidationErrors(BindingResult bindingResult){
        return bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(". "));
    }
}
