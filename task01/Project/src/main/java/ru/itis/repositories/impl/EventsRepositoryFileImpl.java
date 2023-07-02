package ru.itis.repositories.impl;

import ru.itis.models.Event;
import ru.itis.models.User;
import ru.itis.repositories.EventsRepository;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 6/30/2023
 * Repository Example
 *
 * @author Marsel Sidikov (AIT TR)
 */
public class EventsRepositoryFileImpl implements EventsRepository {
    private final String eventFileName;
    private final String eventsAndUsersFileName;

    public EventsRepositoryFileImpl(String eventFileName, String eventsAndUsersFileName) {
        this.eventFileName = eventFileName;
        this.eventsAndUsersFileName = eventsAndUsersFileName;
    }

    @Override
    public void save(Event model) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(eventFileName, true))){
            writer.write(model.getId() + "|" + model.getName() + "|" + model.getDate());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Event findByName(String nameEvent) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(eventFileName));
            String str = reader.readLine();
            while(str != null) {
                if (str.contains(nameEvent)) {
                    return Event.builder()
                            .id(str.substring(0, str.indexOf('|')))
                            .name(str.substring(str.indexOf("|") + 1, str.lastIndexOf("|")))
                            .date(LocalDate.parse(str.substring(str.lastIndexOf("|")+1, str.length())))
                            .build();
                }
                str = reader.readLine();
            }
        return null;
    }

    @Override
    public void saveUserToEvent(User user, Event event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(eventsAndUsersFileName, true))){
            writer.write(user.getId() + "|" + event.getId());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
    @Override
    public Event findByID(String idEvent) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(eventFileName));
        String str = reader.readLine();
        while(str != null) {
            if (str.contains(idEvent)) {
                return Event.builder()
                        .id(str.substring(0, str.indexOf('|')))
                        .name(str.substring(str.indexOf("|") + 1, str.lastIndexOf("|")))
                        .date(LocalDate.parse(str.substring(str.lastIndexOf("|")+1, str.length())))
                        .build();
            }
            str = reader.readLine();
        }
        return null;
    }

    @Override
    public List<Event> findAllByMembersContains(User user) throws IOException {
        List<Event> eventList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(eventsAndUsersFileName));
        String str = reader.readLine();
        while(str != null){
            if(str.contains(user.getId())){
                eventList.add(findByID(str.substring(str.indexOf("|")+1, str.length())));
            }
            str = reader.readLine();
        }
        return eventList;
    }
}
