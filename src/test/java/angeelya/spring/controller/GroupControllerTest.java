package angeelya.spring.controller;

import angeelya.spring.dto.response.GroupResponse;
import angeelya.spring.dto.response.MessageResponse;
import angeelya.spring.exceptionHandler.exception.DeleteException;
import angeelya.spring.exceptionHandler.exception.NoAddException;
import angeelya.spring.exceptionHandler.exception.NotFoundException;
import angeelya.spring.exceptionHandler.exception.ValidationErrorsException;
import angeelya.spring.service.GroupService;
import angeelya.spring.validation.service.ValidationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

import static angeelya.spring.controller.util.TestGroupControllerData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GroupControllerTest {

    @Mock
    private GroupService groupService;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private ValidationService validationService;
    @InjectMocks
    private GroupController groupController;

    @Test
    void shouldGetAllGroups() throws NotFoundException {
        when(groupService.findGroupsAll()).thenReturn(groupResponses);
        ResponseEntity<List<GroupResponse>> responseEntity = groupController.getAllGroups();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
        assertEquals("A-1", responseEntity.getBody().get(0).getName());
        assertEquals("B-2", responseEntity.getBody().get(1).getName());
    }

    @Test
    void shouldAddGroup() throws NoAddException, ValidationErrorsException {
        when(groupService.addGroup(groupAddRequest)).thenReturn(messageResponseAdd);
        ResponseEntity<MessageResponse> responseEntity = groupController.addGroup(groupAddRequest, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MS_SUCCESS_ADD, responseEntity.getBody().getMessage());
        verify(validationService, times(1)).validation(bindingResult);
    }

    @Test
    void shouldUpdateGroup() throws NoAddException, ValidationErrorsException {
        when(groupService.updateGroup(groupUpdateRequest)).thenReturn(messageResponseUpdate);
        ResponseEntity<MessageResponse> responseEntity = groupController.updateGroup(groupUpdateRequest, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MS_SUCCESS_UPDATE, responseEntity.getBody().getMessage());
        verify(validationService, times(1)).validation(bindingResult);
    }

    @Test
    void shouldDeleteGroup() throws DeleteException, ValidationErrorsException {
        when(groupService.deleteGroup(groupRequest)).thenReturn(messageResponseDelete);
        ResponseEntity<MessageResponse> responseEntity = groupController.deleteGroup(groupRequest, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MS_SUCCESS_DELETE, responseEntity.getBody().getMessage());
        verify(validationService, times(1)).validation(bindingResult);
    }

    @Test
    void shouldGetGroupById() throws NotFoundException, ValidationErrorsException {
        when(groupService.findGroupById(groupRequest)).thenReturn(groupResponse);
        ResponseEntity<GroupResponse> responseEntity = groupController.getGroupById(groupRequest, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(groupResponse, responseEntity.getBody());
        verify(validationService, times(1)).validation(bindingResult);
    }

    @Test
    void shouldSearchGroup() throws NotFoundException, ValidationErrorsException {
        when(groupService.findGroupsByKey(searchRequest)).thenReturn(groupResponses);
        ResponseEntity<List<GroupResponse>> responseEntity = groupController.searchGroup(searchRequest, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
        assertEquals("A-1", responseEntity.getBody().get(0).getName());
        assertEquals("B-2", responseEntity.getBody().get(1).getName());
        verify(validationService, times(1)).validation(bindingResult);
    }

    @Test
    void shouldAddTeaching() throws NoAddException, ValidationErrorsException {
        when(groupService.addTeaching(teachingRequest)).thenReturn(messageResponseTeaching);
        ResponseEntity<MessageResponse> responseEntity = groupController.addTeaching(teachingRequest, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MS_SUCCESS_ADD_TEACHING, responseEntity.getBody().getMessage());
        verify(validationService, times(1)).validation(bindingResult);

    }
}