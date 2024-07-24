package tasklist.tasklistweb.web.mappers;

import org.mapstruct.Mapper;
import tasklist.tasklistweb.domain.task.Task;
import tasklist.tasklistweb.domain.task.TaskImage;
import tasklist.tasklistweb.web.dto.task.TaskDto;
import tasklist.tasklistweb.web.dto.task.TaskImageDto;

@Mapper(componentModel = "spring")
public interface TaskImageMapper extends Mappable<TaskImage, TaskImageDto> {
}
