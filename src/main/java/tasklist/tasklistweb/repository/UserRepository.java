package tasklist.tasklistweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tasklist.tasklistweb.domain.user.Role;
import tasklist.tasklistweb.domain.user.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

//    @Query(value = """
//            SELECT u.id as user_id,
//                            u.name as user_name,
//                           u.username as user_username,
//                           u.password as user_password,
//                           ur.role as user_role_role,
//                           t.id as task_id,
//                           t.title as task_title,
//                           t.description as task_description,
//                           t.expiration_date as task_expiration_date,
//                           t.status as task_status
//                FROM users u
//                    LEFT JOIN users_roles ur on u.id = ur.user_id
//                    LEFT JOIN users_tasks ut on u.id = ut.user_id
//                    LEFT JOIN tasks t on ut.task_id = t.id
//                WHERE u.username = :username
//        """, nativeQuery = true)
    Optional<User> findByUsername(String username);

    @Query(value = """
             SELECT exists(
                SELECT 1
                FROM users_tasks
                WHERE user_id = :userId
                    AND task_id = :taskId
            )
            """, nativeQuery = true)
    boolean isTaskOwner(@Param("userId") Long userId, @Param("taskId") Long taskId);

}
