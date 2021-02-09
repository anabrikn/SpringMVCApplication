package ru.ava.springcoursecrudapp.dao;

import org.springframework.stereotype.Component;
import ru.ava.springcoursecrudapp.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "password";

    private static Connection connection;

    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sqlReq = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(sqlReq);
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return people;
    }

    /*
    public Person show(final int id) {
        return people.stream().filter(p -> p.getId() == id).findAny().orElse(null);
    }
    */

    public void save(Person person) {
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO Person VALUES(" +
                    ++PEOPLE_COUNT + ",'" +
                    person.getName() + "'," + person.getAge() + ",'" +
                    person.getEmail() + "')";

            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(int id, Person newPerson) {
//        Person person = show(id);
//        person.setName(newPerson.getName());
//        person.setAge(newPerson.getAge());
//        person.setEmail(newPerson.getEmail());
    }

    public void delete(int id) {
//        people.removeIf(person -> person.getId() == id);
    }
}
