package server.crm.modules.courseclass.services;

import org.springframework.data.domain.Page;
import server.crm.entities.Mark;

/**
 * @author: khoa1
 * @create: 20/11/2018
 */
public interface MarkService {
    Page<Mark> getAllMarkPaginated(int page, int size, String sortBy);

    Page<Mark> searchMarkByKeyWordClassId(int page, int size, String sortBy, String keyWord, String classId);

    Mark getMarkById(String id);

    Mark createMark(Mark mark);

    Mark updateMark(Mark mark);

    boolean deleteMark(String id);

}
