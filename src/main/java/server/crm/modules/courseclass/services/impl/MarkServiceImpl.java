package server.crm.modules.courseclass.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import server.crm.entities.Mark;
import server.crm.modules.courseclass.services.MarkService;

/**
 * @author: khoa1
 * @create: 20/11/2018
 */
@Service
public class MarkServiceImpl implements MarkService {
    @Override
    public Page<Mark> getAllMarkPaginated(int page, int size, String sortBy) {
        return null;
    }

    @Override
    public Page<Mark> searchMarkByKeyWordClassId(int page, int size, String sortBy, String keyWord, String classId) {
        return null;
    }

    @Override
    public Mark getMarkById(String id) {
        return null;
    }

    @Override
    public Mark createMark(Mark mark) {
        return null;
    }

    @Override
    public Mark updateMark(Mark mark) {
        return null;
    }

    @Override
    public boolean deleteMark(String id) {
        return false;
    }
}
