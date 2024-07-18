package tasklist.tasklistweb.service;

import tasklist.tasklistweb.domain.user.User;

public interface UserService {

    User getById(Long id);

    User getByUsername(String Username);

    User update(User user);

    User create(User user);

    boolean isTaskOwner(Long userId, Long takId);

    void delete(Long id);
}
