package tasklist.tasklistweb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tasklist.tasklistweb.domain.MailType;
import tasklist.tasklistweb.domain.task.Task;
import tasklist.tasklistweb.domain.user.User;
import tasklist.tasklistweb.service.MailService;
import tasklist.tasklistweb.service.Reminder;
import tasklist.tasklistweb.service.TaskService;
import tasklist.tasklistweb.service.UserService;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class ReminderImpl implements Reminder {

    private final TaskService taskService;
    private final UserService userService;
    private final MailService emailService;
    private final Duration DURATION = Duration.ofHours(1);


//    @Scheduled(cron = "0 0 * * * *")
    @Scheduled(cron = "0 * * * * *")
    @Override
    public void remindForTask() {
        List<Task> tasks = taskService.getAllSoonTasks(DURATION);
        tasks.forEach(task -> {
            User user = userService.getTaskAuthor(task.getId());
            Properties properties = new Properties();
            properties.setProperty("task.title", task.getTitle());
            properties.setProperty("task.description", task.getDescription());
            emailService.sendMail(user, MailType.REMINDER, properties);
        });
    }
}
