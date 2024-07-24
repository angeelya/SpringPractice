package angeelya.spring.service;

import angeelya.spring.dto.request.*;
import angeelya.spring.dto.response.GroupResponse;
import angeelya.spring.dto.response.MessageResponse;
import angeelya.spring.exceptionHandler.exception.DeleteException;
import angeelya.spring.exceptionHandler.exception.NoAddException;
import angeelya.spring.exceptionHandler.exception.NotFoundException;
import angeelya.spring.mapper.GroupMapper;
import angeelya.spring.model.Group;
import angeelya.spring.repository.GroupRepository;
import jakarta.persistence.TransactionRequiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private static final String MS_FAILED_SAVE = "Failed to save group";
    private static final String MS_SUCCESS_ADD = "Group adding is successful";
    private static final String MS_SUCCESS_UPDATE = "Group updating is successful";
    private static final String MS_FAILED_DELETE = "Failed to delete group";
    private static final String MS_SUCCESS_DELETE = "Group deleting is successful";
    private static final String MS_NOT_FOUND_LIST = "No groups found";
    private static final String MS_NOT_FOUND = "Group not found";
    private static final String MS_FAILED_ADD_TEACHING = "Failed to add teaching";
    private static final String MS_SUCCESS_ADD_TEACHING = "Teaching adding is successful";
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    @Autowired
    public GroupService(GroupRepository groupRepository, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }

    public MessageResponse addGroup(GroupAddRequest groupAddRequest) throws NoAddException {
        Group group = groupMapper.groupAddRequestToGroup(groupAddRequest);
        saveGroup(group);
        return new MessageResponse(MS_SUCCESS_ADD);

    }

    public MessageResponse updateGroup(GroupUpdateRequest groupUpdateRequest) throws NoAddException {
        Group group = groupMapper.groupUpdateRequestToGroup(groupUpdateRequest);
        saveGroup(group);
        return new MessageResponse(MS_SUCCESS_UPDATE);
    }

    public MessageResponse addTeaching(TeachingRequest teachingRequest) throws NoAddException {
        try {
            groupRepository.addTeaching(teachingRequest.getGroupId(), teachingRequest.getTeacherId());
        } catch (DataAccessException e) {
            throw new NoAddException(MS_FAILED_ADD_TEACHING+" "+e.getMessage());
        }
        return new MessageResponse(MS_SUCCESS_ADD_TEACHING);
    }

    public List<GroupResponse> findGroupsAll() throws NotFoundException {
        List<Group> groups = groupRepository.findAll();
        return getGroupResponses(groups);
    }

    public List<GroupResponse> findGroupsByKey(SearchRequest searchRequest) throws NotFoundException {
        List<Group> groups = groupRepository.findGroupsByGroupNameIsLike("%" + searchRequest.getKey() + "%");
        return getGroupResponses(groups);
    }

    public GroupResponse findGroupById(GroupRequest groupRequest) throws NotFoundException {
        Optional<Group> group = groupRepository.findById(groupRequest.getGroupId());
        return getGroupResponse(group);
    }

    public MessageResponse deleteGroup(GroupRequest groupRequest) throws DeleteException {
        delete(groupRequest.getGroupId());
        Optional<Group> group = groupRepository.findById(groupRequest.getGroupId());
        if (group.isPresent()) throw new DeleteException(MS_FAILED_DELETE);
        return new MessageResponse(MS_SUCCESS_DELETE);
    }

    private void delete(Long groupId) throws DeleteException {
        try {
            groupRepository.deleteById(groupId);
        } catch (EmptyResultDataAccessException | TransactionRequiredException e) {
            throw new DeleteException(MS_FAILED_DELETE);
        }
    }

    private void saveGroup(Group group) throws NoAddException {
        try {
            group = groupRepository.save(group);
            if (group.getGroupName() == null) throw new NoAddException(MS_FAILED_SAVE);
        } catch (DataAccessException e) {
            throw new NoAddException(MS_FAILED_SAVE);
        }
    }

    private GroupResponse getGroupResponse(Optional<Group> group) throws NotFoundException {
        if (group.isEmpty()) throw new NotFoundException(MS_NOT_FOUND);
        return groupMapper.toGroupResponse(group.get());
    }

    private List<GroupResponse> getGroupResponses(List<Group> groups) throws NotFoundException {
        if (groups.isEmpty()) throw new NotFoundException(MS_NOT_FOUND_LIST);
        return groupMapper.toGroupResponses(groups);
    }
}
