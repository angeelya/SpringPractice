package angeelya.spring.controller;

import angeelya.spring.dto.request.DisciplineAddRequest;
import angeelya.spring.dto.request.DisciplineUpdateRequest;
import angeelya.spring.dto.request.SearchRequest;
import angeelya.spring.dto.response.DisciplineResponse;
import angeelya.spring.dto.response.MessageResponse;
import angeelya.spring.exceptionHandler.exception.NoAddException;
import angeelya.spring.exceptionHandler.exception.NotFoundException;
import angeelya.spring.exceptionHandler.exception.ValidationErrorsException;
import angeelya.spring.service.DisciplineService;
import angeelya.spring.validation.service.ValidationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discipline")
public class DisciplineController {
    private final DisciplineService disciplineService;
    private final ValidationService validationService;
    @Autowired
    public DisciplineController(DisciplineService disciplineService, ValidationService validationService) {
        this.disciplineService = disciplineService;
        this.validationService = validationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DisciplineResponse>> getAllDisciplines() throws NotFoundException {
        return ResponseEntity.ok(disciplineService.findDisciplineAll());
    }
    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addDiscipline(@RequestBody @Valid DisciplineAddRequest disciplineAddRequest, BindingResult bindingResult) throws ValidationErrorsException, NoAddException {
        validationService.validation(bindingResult);
        return ResponseEntity.ok(disciplineService.addDiscipline(disciplineAddRequest));
    }
    @PutMapping("/update")
    public ResponseEntity<MessageResponse> updateDiscipline(@RequestBody @Valid DisciplineUpdateRequest disciplineUpdateRequest,BindingResult bindingResult) throws ValidationErrorsException, NoAddException {
        validationService.validation(bindingResult);
        return ResponseEntity.ok(disciplineService.updateDiscipline(disciplineUpdateRequest));
    }
    @PostMapping("/search")
    public ResponseEntity<List<DisciplineResponse>> searchDiscipline(@RequestBody @Valid SearchRequest searchRequest, BindingResult bindingResult) throws ValidationErrorsException, NotFoundException {
        validationService.validation(bindingResult);
        return ResponseEntity.ok(disciplineService.findDisciplineKey(searchRequest));
    }
}
