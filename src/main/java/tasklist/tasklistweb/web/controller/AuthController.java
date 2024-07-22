package tasklist.tasklistweb.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tasklist.tasklistweb.domain.user.User;
import tasklist.tasklistweb.service.AuthService;
import tasklist.tasklistweb.service.UserService;
import tasklist.tasklistweb.web.dto.auth.JwtRequest;
import tasklist.tasklistweb.web.dto.auth.JwtResponse;
import tasklist.tasklistweb.web.dto.user.UserDto;
import tasklist.tasklistweb.web.dto.validation.OnCreate;
import tasklist.tasklistweb.web.mappers.UserMapper;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@Validated
@Tag(name = "Auth Controller", description = "Auth API")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody JwtRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public UserDto register(@Validated(OnCreate.class) @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User createdUser = userService.create(user);
        return userMapper.toDto(createdUser);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken) {
        return authService.refresh(refreshToken);
    }
}
