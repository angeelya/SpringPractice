package angeelya.spring.service;

import angeelya.spring.database.repository.GroupRepository;
import angeelya.spring.dto.response.GroupResponse;
import angeelya.spring.dto.response.MessageResponse;
import angeelya.spring.exceptionHandler.exception.DeleteException;
import angeelya.spring.exceptionHandler.exception.NoAddException;
import angeelya.spring.exceptionHandler.exception.NotFoundException;
import angeelya.spring.mapper.GroupMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static angeelya.spring.service.util.TestGroupData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {
    @Mock
    GroupRepository groupRepository;
    @Mock
    GroupMapper groupMapper;
    @InjectMocks
    GroupService groupService;

    @Test
    void shouldAddGroup() throws NoAddException {
        when(groupMapper.groupAddRequestToGroup(groupAddRequest)).thenReturn(groupAdd);
        when(groupRepository.save(groupAdd)).thenReturn(groupAdd);
        MessageResponse actual = groupService.addGroup(groupAddRequest);
        assertEquals(MS_SUCCESS_ADD, actual.getMessage());
    }

    @Test
    void shouldAddGroupThrowNoAddException() {
        when(groupMapper.groupAddRequestToGroup(groupAddRequest)).thenReturn(groupAdd);
        when(groupRepository.save(groupAdd)).thenReturn(groupEmpty);
        assertThrows(NoAddException.class, () -> groupService.addGroup(groupAddRequest));
    }

    @Test
    void shouldUpdateGroup() throws NoAddException {
        when(groupMapper.groupUpdateRequestToGroup(groupUpdateRequest)).thenReturn(group);
        when(groupRepository.save(group)).thenReturn(group);
        MessageResponse actual = groupService.updateGroup(groupUpdateRequest);
        assertEquals(MS_SUCCESS_UPDATE, actual.getMessage());
    }

    @Test
    void shouldUpdateGroupThrowNoAddException() {
        when(groupMapper.groupUpdateRequestToGroup(groupUpdateRequest)).thenReturn(group);
        when(groupRepository.save(group)).thenReturn(groupEmpty);
        assertThrows(NoAddException.class, () -> groupService.updateGroup(groupUpdateRequest));
    }

    @Test
    void shouldAddTeaching() throws NoAddException {
        MessageResponse actual =groupService.addTeaching(teachingRequest);
        assertEquals(MS_SUCCESS_ADD_TEACHING,actual.getMessage());
    }

    @Test
    void shouldFindGroupsAll() throws NotFoundException {
        when(groupRepository.findAll()).thenReturn(groups);
        when(groupMapper.toGroupResponses(groups)).thenReturn(groupResponses);
        List<GroupResponse> actual = groupService.findGroupsAll();
        assertEquals(groupResponses,actual);
    }
    @Test
    void shouldFindGroupsAllThrowNotFoundException() throws NotFoundException {
        when(groupRepository.findAll()).thenReturn(groupsEmpty);
        assertThrows(NotFoundException.class,()->groupService.findGroupsAll());
    }

    @Test
    void shouldFindGroupsByKey() throws NotFoundException {
     when(groupRepository.findGroupsByGroupNameIsLike(any(String.class))).thenReturn(groups);
     when(groupMapper.toGroupResponses(groups)).thenReturn(groupResponses);
        List<GroupResponse> actual = groupService.findGroupsByKey(searchRequest);
        assertEquals(groupResponses,actual);
    }
    @Test
    void shouldFindGroupsByKeyThrowNotFoundException() throws NotFoundException {
        when(groupRepository.findGroupsByGroupNameIsLike(any(String.class))).thenReturn(groupsEmpty);
        assertThrows(NotFoundException.class,()->groupService.findGroupsByKey(searchRequest));
    }

    @Test
    void shouldFindGroupById() throws NotFoundException {
        when(groupRepository.findById(groupRequest.getGroupId())).thenReturn(Optional.ofNullable(group));
        when(groupMapper.toGroupResponse(group)).thenReturn(groupResponse);
        GroupResponse actual = groupService.findGroupById(groupRequest);
        assertEquals(groupResponse,actual);
    }
    @Test
    void shouldFindGroupByIdThrowNotFoundException () {
        when(groupRepository.findById(groupRequest.getGroupId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,()->groupService.findGroupById(groupRequest));
    }

    @Test
    void shouldDeleteGroup() throws DeleteException {
        doNothing().when(groupRepository).deleteById(anyLong());
        when(groupRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        MessageResponse actual = groupService.deleteGroup(groupRequest);
        assertEquals(MS_SUCCESS_DELETE,actual.getMessage());
    }
    @Test
    void shouldDeleteGroupThrowDeleteException() {
        doNothing().when(groupRepository).deleteById(anyLong());
        when(groupRepository.findById(any(Long.class))).thenReturn(Optional.of(group));
        assertThrows(DeleteException.class,()->groupService.deleteGroup(groupRequest));
    }
}