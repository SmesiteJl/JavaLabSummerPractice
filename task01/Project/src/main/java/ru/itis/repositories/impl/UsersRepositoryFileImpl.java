package ru.itis.repositories.impl;

import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import java.io.*;

/**
 * 6/30/2023
 * Repository Example
 *
 * @author Marsel Sidikov (AIT TR)
 */
public class UsersRepositoryFileImpl implements UsersRepository {

    private final String fileName;

    public UsersRepositoryFileImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(User model) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            writer.write(model.getId() + "|" + model.getEmail() + "|" + model.getPassword());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public User findByEmail(String emailUser) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String str = reader.readLine();
        while (str != null){
            if(str.contains(emailUser)){
                return User.builder()
                        .id(str.substring(0, str.indexOf("|")))
                        .email(str.substring(str.indexOf("|")+1, str.lastIndexOf("|")))
                        .password(str.substring(str.lastIndexOf("|")+1, str.length()))
                        .build();
            }
            str = reader.readLine();
        }
        return null;
    }
}
