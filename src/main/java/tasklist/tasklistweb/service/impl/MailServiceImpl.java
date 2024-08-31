package tasklist.tasklistweb.service.impl;

import freemarker.template.Configuration;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tasklist.tasklistweb.domain.MailType;
import tasklist.tasklistweb.domain.user.User;
import tasklist.tasklistweb.service.MailService;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final Configuration configuration;
    private final JavaMailSender mailSender;

    @Override
    public void sendMail(User user, MailType type, Properties params) {
        switch (type) {
            case REGISTRATION -> sendRegistrationMail(user, params);
            case REMINDER -> sendReminderMail(user, params);
            default -> {}
        }
    }

    @SneakyThrows
    private void sendRegistrationMail(User user, Properties params) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper  = new MimeMessageHelper(mimeMessage, false, "UTF-8");
        helper.setSubject("Thank for registration" + user.getName());
        helper.setTo(user.getUsername());
        String emailContent = getRegistrationMailContent(user, params);
        helper.setText(emailContent, true);
        mailSender.send(mimeMessage);
    }

    @SneakyThrows
    private void sendReminderMail(User user, Properties params) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper  = new MimeMessageHelper(mimeMessage, false, "UTF-8");
        helper.setSubject("You have task to do in 1 hour");
        helper.setTo(user.getUsername());
        String emailContent = getReminderMailContent(user, params);
        helper.setText(emailContent, true);
        mailSender.send(mimeMessage);
    }

    @SneakyThrows
    private String getRegistrationMailContent(User user, Properties params) {
        StringWriter writer = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("name", user.getName());
        configuration.getTemplate("register.ftlh")
                .process(model, writer);
        return writer.getBuffer().toString();
    }

    @SneakyThrows
    private String getReminderMailContent(User user, Properties properties) {
        StringWriter writer = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("name", user.getName());
        model.put("title", properties.getProperty("task.title"));
        model.put("description", properties.getProperty("task.description"));
        configuration.getTemplate("reminder.ftlh")
                .process(model, writer);
        return writer.getBuffer().toString();
    }



}
