package tasklist.tasklistweb.web.mappers;

import org.mapstruct.Mapper;
import tasklist.tasklistweb.domain.task.Task;
import tasklist.tasklistweb.web.dto.task.TaskDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDto toDto(Task task);

    List<TaskDto> toDto(List<Task> tasks);

    Task toEntity(TaskDto dto);
}
