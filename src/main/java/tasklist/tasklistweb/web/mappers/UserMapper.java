package tasklist.tasklistweb.web.dto.mappers;

import org.mapstruct.Mapper;
import tasklist.tasklistweb.domain.user.User;
import tasklist.tasklistweb.web.dto.user.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto dto);
}
