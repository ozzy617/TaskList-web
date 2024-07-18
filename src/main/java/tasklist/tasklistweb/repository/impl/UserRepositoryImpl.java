package tasklist.tasklistweb.repository.impl;

import org.springframework.stereotype.Repository;
import tasklist.tasklistweb.domain.user.Role;
import tasklist.tasklistweb.domain.user.User;
import tasklist.tasklistweb.repository.UserRepository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return Optional.empty();
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void create(User user) {

    }

    @Override
    public void insertUserRole(Long userId, Role role) {

    }

    @Override
    public boolean isTaskOwner(Long userId, Long taskId) {
        return false;
    }

    @Override
    public void deleter(Long id) {

    }
}
