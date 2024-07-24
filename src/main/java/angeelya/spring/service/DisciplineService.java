package angeelya.spring.service;

import angeelya.spring.dto.request.DisciplineAddRequest;
import angeelya.spring.dto.request.DisciplineUpdateRequest;
import angeelya.spring.dto.request.SearchRequest;
import angeelya.spring.dto.response.DisciplineResponse;
import angeelya.spring.dto.response.MessageResponse;
import angeelya.spring.exceptionHandler.exception.NoAddException;
import angeelya.spring.exceptionHandler.exception.NotFoundException;
import angeelya.spring.mapper.DisciplineMapper;
import angeelya.spring.model.Discipline;
import angeelya.spring.repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplineService {
    private static final String MS_SUCCESS_ADD = "Discipline adding is successful";
    private static final String MS_FAILED_SAVE = "Failed to save discipline";
    private static final String MS_SUCCESS_UPDATE = "Discipline updating is successful";
    private static final String MS_NOT_FOUND_LIST = "No discipline found";
    private final DisciplineRepository disciplineRepository;
    private final DisciplineMapper disciplineMapper;

    @Autowired

    public DisciplineService(DisciplineRepository disciplineRepository, DisciplineMapper disciplineMapper) {
        this.disciplineRepository = disciplineRepository;
        this.disciplineMapper = disciplineMapper;
    }

    public MessageResponse addDiscipline(DisciplineAddRequest disciplineAddRequest) throws NoAddException {
        Discipline discipline = disciplineMapper.disciplineAddRequestToDiscipline(disciplineAddRequest);
        saveDiscipline(discipline);
        return new MessageResponse(MS_SUCCESS_ADD);
    }

    public MessageResponse updateDiscipline(DisciplineUpdateRequest disciplineUpdateRequest) throws NoAddException {
        Discipline discipline = disciplineMapper.disciplineUpdateRequestToDiscipline(disciplineUpdateRequest);
        saveDiscipline(discipline);
        return new MessageResponse(MS_SUCCESS_UPDATE);
    }

    public List<DisciplineResponse> findDisciplineAll() throws NotFoundException {
        List<Discipline> disciplines = disciplineRepository.findAll();
        return getDisciplineResponses(disciplines);
    }

    public List<DisciplineResponse> findDisciplineKey(SearchRequest searchRequest) throws NotFoundException {
        List<Discipline> disciplines = disciplineRepository.findDisciplinesByDisciplineNameIsLike("%" + searchRequest.getKey() + "%");
        return getDisciplineResponses(disciplines);
    }

    private void saveDiscipline(Discipline discipline) throws NoAddException {
        try {
            discipline = disciplineRepository.save(discipline);
            if (discipline.getDisciplineName() == null) throw new NoAddException(MS_FAILED_SAVE);
        } catch (DataAccessException e) {
            throw new NoAddException(MS_FAILED_SAVE);
        }
    }

    private List<DisciplineResponse> getDisciplineResponses(List<Discipline> disciplines) throws NotFoundException {
        if (disciplines.isEmpty()) throw new NotFoundException(MS_NOT_FOUND_LIST);
        return disciplineMapper.toDisciplineResponses(disciplines);
    }
}
