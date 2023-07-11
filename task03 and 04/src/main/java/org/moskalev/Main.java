package org.moskalev;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.moskalev.models.Course;
import org.moskalev.models.Student;
import org.moskalev.repositories.*;

public class Main {
    public static void main(String[] args) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/uni_db");
        hikariConfig.setUsername("javaProjectUser");
        hikariConfig.setPassword("qwerty001");
        hikariConfig.setDriverClassName("org.postgresql.Driver");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        StudentsRepository studentsRepository = new StudentsRepositoryJdbcImpl(dataSource);
        CourseRepository courseRepository = new CourseRepositoryJdbcImpl(dataSource);

        Student student = Student.builder()
                .firstName("Name123")
                .lastName("surname123")
                .age(45)
                .build();
        System.out.println(student);
        studentsRepository.save(student);
        System.out.println(student);
        System.out.println(studentsRepository.findAll());

        Course course = Course.builder()
                .title("java practice")
                .startTime("2023-07-01")
                .endTime("2023-07-12")
                .build();
        System.out.println(course);
        courseRepository.save(course);
        System.out.println(course);
        System.out.println(courseRepository.findAll());

        courseRepository = new CoursesRepositorySpringJdbcImpl(dataSource);
        Course course2 = Course.builder()
                .title("javaLab practice")
                .startTime("2023-08-05")
                .endTime("2023-09-14")
                .build();
        System.out.println(course2);
        courseRepository.save(course2);
        System.out.println(course2);
        System.out.println(courseRepository.findAll());



    }
}