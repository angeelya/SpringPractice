package angeelya.spring.controller;

import angeelya.spring.dto.request.*;
import angeelya.spring.dto.response.GroupResponse;
import angeelya.spring.dto.response.MessageResponse;
import angeelya.spring.exceptionHandler.exception.DeleteException;
import angeelya.spring.exceptionHandler.exception.NoAddException;
import angeelya.spring.exceptionHandler.exception.NotFoundException;
import angeelya.spring.exceptionHandler.exception.ValidationErrorsException;
import angeelya.spring.service.GroupService;
import angeelya.spring.validation.service.ValidationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private ValidationService validationService;
    @GetMapping("/all")
    public ResponseEntity<List<GroupResponse>> getAllGroups() throws NotFoundException {
        return ResponseEntity.ok(groupService.findGroupsAll());
    }
    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addGroup(@RequestBody @Valid GroupAddRequest groupAddRequest, BindingResult bindingResult) throws ValidationErrorsException, NoAddException {
        validationService.validation(bindingResult);
        return ResponseEntity.ok(groupService.addGroup(groupAddRequest));
    }
    @PutMapping("/update")
    public ResponseEntity<MessageResponse> updateGroup(@RequestBody @Valid GroupUpdateRequest groupUpdateRequest, BindingResult bindingResult) throws ValidationErrorsException, NoAddException {
        validationService.validation(bindingResult);
        return ResponseEntity.ok(groupService.updateGroup(groupUpdateRequest));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<MessageResponse> deleteGroup(@RequestBody @Valid GroupRequest groupRequest,BindingResult bindingResult) throws ValidationErrorsException, DeleteException {
        validationService.validation(bindingResult);
        return ResponseEntity.ok(groupService.deleteGroup(groupRequest));
    }
    @PostMapping("/get/data")
    public ResponseEntity<GroupResponse> getGroupById(@RequestBody @Valid GroupRequest groupRequest, BindingResult bindingResult) throws ValidationErrorsException, NotFoundException {
        validationService.validation(bindingResult);
        return ResponseEntity.ok(groupService.findGroupById(groupRequest));
    }
    @PostMapping("/search")
    public ResponseEntity<List<GroupResponse>> searchGroup(@RequestBody @Valid SearchRequest searchRequest, BindingResult bindingResult) throws ValidationErrorsException, NotFoundException {
        validationService.validation(bindingResult);
        return ResponseEntity.ok(groupService.findGroupsByKey(searchRequest));
    }
    @PostMapping("/teaching")
    public ResponseEntity<MessageResponse>addTeaching(@RequestBody @Valid TeachingRequest teachingRequest, BindingResult bindingResult) throws ValidationErrorsException, NoAddException {
        validationService.validation(bindingResult);
        return ResponseEntity.ok(groupService.addTeaching(teachingRequest));
    }
}
