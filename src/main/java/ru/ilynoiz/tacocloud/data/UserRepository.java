package ru.ilynoiz.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import ru.ilynoiz.tacocloud.security.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
