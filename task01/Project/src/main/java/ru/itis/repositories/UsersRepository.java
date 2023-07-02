package ru.itis.repositories;

import ru.itis.models.User;

import java.io.IOException;

/**
 * 6/30/2023
 * Repository Example
 *
 * @author Marsel Sidikov (AIT TR)
 */
public interface UsersRepository extends CrudRepository<User> {
    User findByEmail(String emailUser) throws IOException;
}
