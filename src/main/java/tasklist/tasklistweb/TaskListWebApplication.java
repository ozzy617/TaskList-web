package tasklist.tasklistweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TaskListWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskListWebApplication.class, args);
    }

}
