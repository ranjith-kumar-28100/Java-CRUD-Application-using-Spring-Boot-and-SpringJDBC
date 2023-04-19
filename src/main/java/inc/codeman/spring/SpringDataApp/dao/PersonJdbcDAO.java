package inc.codeman.spring.SpringDataApp.dao;

import inc.codeman.spring.SpringDataApp.entity.Person;

import java.util.List;


public interface PersonJdbcDAO {
    List<Person> findAll();
    Person findById(int id);
    List<Person> findByName(String name);
    List<Person> findByLocation(String location);
    List<Person> findByNameAndLocation(String name, String location);
    int deleteById(int id);
    int insert (Person person);
    int update (Person person);
}
