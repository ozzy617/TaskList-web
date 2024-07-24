package tasklist.tasklistweb.web.mappers;

import org.mapstruct.Mapper;
import tasklist.tasklistweb.domain.user.User;
import tasklist.tasklistweb.web.dto.user.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto>{
}
