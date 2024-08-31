package tasklist.tasklistweb.service;

import tasklist.tasklistweb.domain.MailType;
import tasklist.tasklistweb.domain.user.User;

import java.util.Map;
import java.util.Properties;

public interface MailService {

    void sendMail(User user, MailType type, Properties params);
}
