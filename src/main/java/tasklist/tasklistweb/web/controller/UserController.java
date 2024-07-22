package tasklist.tasklistweb.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tasklist.tasklistweb.domain.task.Task;
import tasklist.tasklistweb.domain.user.User;
import tasklist.tasklistweb.service.TaskService;
import tasklist.tasklistweb.service.UserService;
import tasklist.tasklistweb.web.dto.task.TaskDto;
import tasklist.tasklistweb.web.dto.user.UserDto;
import tasklist.tasklistweb.web.dto.validation.OnCreate;
import tasklist.tasklistweb.web.dto.validation.OnUpdate;
import tasklist.tasklistweb.web.mappers.TaskMapper;
import tasklist.tasklistweb.web.mappers.UserMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
@Tag(name = "User Controller", description = "User API")
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    private final UserMapper userMapper;
    private final TaskMapper taskMapper;

    @PutMapping
    @Operation(summary = "Update user")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#dto.id)")
    public UserDto update(@Validated(OnUpdate.class) @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User updatedUser = userService.update(user);
        return userMapper.toDto(updatedUser);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get UserDto by id")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public UserDto getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user dy id")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public void deleteById(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}/tasks")
    @Operation(summary = "Get all User tasks")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public List<TaskDto> getTasByUserId(@PathVariable Long id) {
        List<Task> tasks = taskService.getAllByUserId(id);
        return taskMapper.toDto(tasks);
    }

    @PostMapping("/{id}/tasks")
    @Operation(summary = "Add task to user")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#dto.id)")
    public TaskDto createTask(@PathVariable Long id, @Validated(OnCreate.class) @RequestBody TaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        Task createdTask = taskService.create(task, id);
        return taskMapper.toDto(createdTask);
    }
}
