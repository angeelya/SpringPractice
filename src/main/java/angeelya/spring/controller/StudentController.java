package angeelya.spring.controller;

import angeelya.spring.dto.request.*;
import angeelya.spring.dto.response.MessageResponse;
import angeelya.spring.dto.response.StudentResponse;
import angeelya.spring.exception.DeleteException;
import angeelya.spring.exception.NoAddException;
import angeelya.spring.exception.NotFoundException;
import angeelya.spring.exception.ValidationErrorsException;
import angeelya.spring.service.StudentService;
import angeelya.spring.validation.service.ValidationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ValidationService validationService;

    @GetMapping("/all")
    public ResponseEntity<List<StudentResponse>> getAllStudents() throws NotFoundException {
        return ResponseEntity.ok(studentService.findAllStudents());
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addStudent(@RequestBody @Valid StudentAddRequest studentAddRequest, BindingResult bindingResult) throws ValidationErrorsException, NoAddException {
        validationService.validation(bindingResult);
        return ResponseEntity.ok(studentService.addStudent(studentAddRequest));
    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> updateStudent(@RequestBody @Valid StudentUpdateRequest studentUpdateRequest, BindingResult bindingResult) throws ValidationErrorsException, NoAddException {
        validationService.validation(bindingResult);
        return ResponseEntity.ok(studentService.updateStudent(studentUpdateRequest));
    }

    @PostMapping("/get/data")
    public ResponseEntity<StudentResponse> getStudentById(@RequestBody @Valid StudentRequest studentRequest, BindingResult bindingResult) throws ValidationErrorsException, NotFoundException {
        validationService.validation(bindingResult);
        return ResponseEntity.ok(studentService.findStudentById(studentRequest));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<MessageResponse> deleteStudent(@RequestBody @Valid StudentRequest studentRequest, BindingResult bindingResult) throws ValidationErrorsException, DeleteException {
        validationService.validation(bindingResult);
        return ResponseEntity.ok(studentService.deleteStudent(studentRequest));
    }
    @PostMapping("/get/by/group")
    public ResponseEntity<List<StudentResponse>>getStudentsByGroupId(@RequestBody @Valid GroupRequest groupRequest, BindingResult bindingResult) throws ValidationErrorsException, NotFoundException {
        validationService.validation(bindingResult);
        return ResponseEntity.ok(studentService.findStudentByGroup(groupRequest));
    }
    @PostMapping("/search")
    public ResponseEntity<List<StudentResponse>> searchStudent(@RequestBody @Valid SearchRequest searchRequest, BindingResult bindingResult) throws ValidationErrorsException, NotFoundException {
        validationService.validation(bindingResult);
        return ResponseEntity.ok(studentService.findStudentsByKey(searchRequest));
    }

}
