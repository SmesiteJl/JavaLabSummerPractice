package ru.itis.repositories;

import ru.itis.models.Event;
import ru.itis.models.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * 6/30/2023
 * Repository Example
 *
 * @author Marsel Sidikov (AIT TR)
 */
public interface EventsRepository extends CrudRepository<Event> {
    Event findByName(String nameEvent) throws IOException;

    void saveUserToEvent(User user, Event event);
    List<Event> findAllByMembersContains(User user) throws IOException;
    Event findByID(String nameEvent) throws IOException;
}
