package angeelya.spring.controller;

import angeelya.spring.dto.request.*;
import angeelya.spring.dto.response.MessageResponse;
import angeelya.spring.dto.response.TeacherResponse;
import angeelya.spring.exception.DeleteException;
import angeelya.spring.exception.NoAddException;
import angeelya.spring.exception.NotFoundException;
import angeelya.spring.exception.ValidationErrorsException;
import angeelya.spring.service.TeacherService;
import angeelya.spring.validation.service.ValidationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ValidationService validationService;
    @GetMapping("/all")
    public ResponseEntity<List<TeacherResponse>> getAllTeachers() throws NotFoundException {
        return ResponseEntity.ok(teacherService.findAllTeachers());
    }
    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addTeacher(@RequestBody @Valid TeacherAddRequest teacherAddRequest, BindingResult bindingResult) throws ValidationErrorsException, NoAddException {
        validationService.validation(bindingResult);
        return ResponseEntity.ok(teacherService.addTeacher(teacherAddRequest));
    }
    @PutMapping("/update")
    public ResponseEntity<MessageResponse> updateTeacher(@RequestBody @Valid TeacherUpdateRequest teacherUpdateRequest, BindingResult bindingResult) throws ValidationErrorsException, NoAddException {
        validationService.validation(bindingResult);
        return ResponseEntity.ok(teacherService.updateTeacher(teacherUpdateRequest));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<MessageResponse> deleteTeacher(@RequestBody @Valid TeacherRequest teacherRequest, BindingResult bindingResult) throws ValidationErrorsException, DeleteException {
        validationService.validation(bindingResult);
        return ResponseEntity.ok(teacherService.deleteTeacher(teacherRequest));
    }
    @PostMapping("/get/data")
    public ResponseEntity<TeacherResponse> getTeacherById(@RequestBody @Valid TeacherRequest teacherRequest, BindingResult bindingResult) throws ValidationErrorsException, NotFoundException {
        validationService.validation(bindingResult);
        return ResponseEntity.ok(teacherService.findTeacherById(teacherRequest));
    }
    @PostMapping("/get/by/discipline")
    public ResponseEntity<List<TeacherResponse>> getTeacherByDiscipline(@RequestBody @Valid DisciplineRequest disciplineRequest, BindingResult bindingResult) throws ValidationErrorsException, NotFoundException {
        validationService.validation(bindingResult);
        return ResponseEntity.ok(teacherService.findTeachersByDisciplineId(disciplineRequest));
    }
    @PostMapping("/search")
    public ResponseEntity<List<TeacherResponse>>searchTeacher(@RequestBody @Valid SearchRequest searchRequest, BindingResult bindingResult) throws ValidationErrorsException, NotFoundException {
        validationService.validation(bindingResult);
        return ResponseEntity.ok(teacherService.findTeachersByKey(searchRequest));
    }
}
