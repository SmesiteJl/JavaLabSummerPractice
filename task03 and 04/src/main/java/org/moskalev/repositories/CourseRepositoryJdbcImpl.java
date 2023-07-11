package org.moskalev.repositories;
import org.moskalev.models.Course;
import javax.sql.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRepositoryJdbcImpl implements CourseRepository {
    //language=sql
    private final static String SQL_SELECT_ALL = "select * from student";

    //language=sql
    private final static String SQL_INSERT = "insert into student(first_name, last_name, age) values (?, ?, ?)";

    private final DataSource dataSource;
    public CourseRepositoryJdbcImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }
    public void save(Course model){
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, model.getTitle());
                statement.setDate(2, Date.valueOf(model.getStartTime()));
                statement.setDate(3, Date.valueOf(model.getEndTime()));
                int affectedRows = statement.executeUpdate();
                if (affectedRows != 1) {
                    throw new SQLException("Cannot insert student");
                }

                try (ResultSet generatedIds = statement.getGeneratedKeys()){
                    if (generatedIds.next()) {
                        model.setId(generatedIds.getInt("id"));
                    } else {
                        throw new SQLException("Cannot retrieve id");
                    }
                }

            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)) {
                while (resultSet.next()) {
                    Course course = Course.builder()
                            .id(resultSet.getInt("id"))
                            .title(resultSet.getString("title"))
                            .startTime(resultSet.getString("start_time"))
                            .endTime(resultSet.getString("end_time"))
                            .build();

                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return courses;
    }

}
