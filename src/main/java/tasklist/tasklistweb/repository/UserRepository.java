package tasklist.tasklistweb.repository.impl;

import tasklist.tasklistweb.domain.user.Role;
import tasklist.tasklistweb.domain.user.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);

    Optional<User> findByUserName(String username);

    void update(User user);

    void create(User user);

    void insertUserRole(Long userId, Role role);

    boolean isTaskOwner(Long userId, Long taskId);

    void deleter(Long id);
}
