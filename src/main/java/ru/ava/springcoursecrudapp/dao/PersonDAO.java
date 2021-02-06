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
        people.add(new Person(++PEOPLE_COUNT, "Kate"));
        people.add(new Person(++PEOPLE_COUNT, "Sarah"));
        people.add(new Person(++PEOPLE_COUNT, "Leo"));
        people.add(new Person(++PEOPLE_COUNT, "Sam"));
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
}
