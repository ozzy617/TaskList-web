package tasklist.tasklistweb.service.impl;

import org.springframework.stereotype.Service;
import tasklist.tasklistweb.domain.user.User;
import tasklist.tasklistweb.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public User getByUsername(String Username) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public boolean isTaskOwner(Long userId, Long takId) {
        return false;
    }

    @Override
    public void delete(Long id) {

    }
}
