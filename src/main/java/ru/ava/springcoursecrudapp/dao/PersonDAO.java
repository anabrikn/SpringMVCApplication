package ru.ava.springcoursecrudapp.dao;

import org.springframework.stereotype.Component;
import ru.ava.springcoursecrudapp.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Kate", 19, "kate@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Sarah", 23, "sarah@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Leo", 20, "leo@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Sam", 31, "sam@gmail.com"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(final int id) {
        return people.stream().filter(p -> p.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person newPerson) {
        Person person = show(id);
        person.setName(newPerson.getName());
        person.setAge(newPerson.getAge());
        person.setEmail(newPerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
    }
}
