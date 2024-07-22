package tasklist.tasklistweb.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tasklist.tasklistweb.domain.task.Task;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TaskRepository {

    Optional<Task> findById(Long id);

    List<Task> findAllByUserId(Long userId);

    void assignToUserById(@Param("taskId") Long taskId, @Param("usrId") Long userId);

    void update(Task task);

    void create(Task task);

    void delete(Long id);
}
