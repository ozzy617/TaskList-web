package tasklist.tasklistweb.config;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import tasklist.tasklistweb.repository.TaskRepository;
import tasklist.tasklistweb.repository.UserRepository;
import tasklist.tasklistweb.service.AuthService;
import tasklist.tasklistweb.service.ImageService;
import tasklist.tasklistweb.service.TaskService;
import tasklist.tasklistweb.service.UserService;
import tasklist.tasklistweb.service.impl.AuthServiceImpl;
import tasklist.tasklistweb.service.impl.ImageServiceImpl;
import tasklist.tasklistweb.service.impl.TaskServiceImpl;
import tasklist.tasklistweb.service.impl.UserServiceImpl;
import tasklist.tasklistweb.service.props.JwtProperties;
import tasklist.tasklistweb.service.props.MinioProperties;
import tasklist.tasklistweb.web.security.JwtTokenProvider;
import tasklist.tasklistweb.web.security.JwtUserDetailsService;

@SpringBootTest(classes = ApplicationRunner.class)
@TestConfiguration
@RequiredArgsConstructor
public class TestConfig {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final AuthenticationManager authenticationManager;

    @Bean
    @Primary
    public PasswordEncoder testPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtProperties jwtProperties() {
        JwtProperties jwtProperties = new JwtProperties();
        jwtProperties.setSecret("amxpZmRuLm1mLC9hZCBtYi5uZmRhIG12Ymosc25qa2MubQ");
        return jwtProperties;
    }

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        return new JwtUserDetailsService(userService());
    }

    @Bean
    public MinioClient minioClient() {
        return Mockito.mock(MinioClient.class);
    }

    @Bean
    public MinioProperties minioProperties() {
        MinioProperties minioProperties = new MinioProperties();
        minioProperties.setBucket("images");
        return minioProperties;
    }

    @Bean
    @Primary
    public ImageService imageService() {
        return new ImageServiceImpl(minioClient(), minioProperties());
    }

    @Bean
    public JwtTokenProvider tokenProvider() {
        return new JwtTokenProvider(jwtProperties(),
                userDetailsService(),
                userService());
    }

    @Bean
    @Primary
    public UserService userService() {
        return new UserServiceImpl(userRepository, testPasswordEncoder());
    }

    @Bean
    @Primary
    public TaskService taskService() {
        return new TaskServiceImpl(taskRepository, userService(), imageService());
    }

    @Bean
    @Primary
    public AuthService authService() {
        return new AuthServiceImpl(authenticationManager, userService(), tokenProvider());
    }

}
