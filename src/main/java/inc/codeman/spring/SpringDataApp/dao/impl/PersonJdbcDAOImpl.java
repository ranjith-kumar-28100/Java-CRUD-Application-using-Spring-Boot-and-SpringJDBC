package inc.codeman.spring.SpringDataApp.dao.impl;

import inc.codeman.spring.SpringDataApp.dao.PersonJdbcDAO;
import inc.codeman.spring.SpringDataApp.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public class PersonJdbcDAOImpl implements PersonJdbcDAO {
    @Autowired
    private  JdbcTemplate jdbcTemplate;

    class PersonRowMapper implements RowMapper<Person>{

        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Person(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getTimestamp(4));
        }
    }
    @Override
    public List<Person> findAll() {
        return jdbcTemplate.query("SELECT * FROM person",new PersonRowMapper());
    }

    @Override
    public Person findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM person WHERE id =?",new Object[]{id},new PersonRowMapper());
    }

    @Override
    public List<Person> findByName(String name) {
        return jdbcTemplate.query("SELECT * FROM person WHERE name =?",new Object[]{name},new BeanPropertyRowMapper<>(Person.class));
    }

    @Override
    public List<Person> findByLocation(String location) {
        return jdbcTemplate.query("SELECT * FROM person WHERE location =?",new Object[]{location},new BeanPropertyRowMapper<>(Person.class));
    }

    @Override
    public List<Person> findByNameAndLocation(String name, String location) {
        return jdbcTemplate.query("SELECT * FROM person WHERE name =? AND location =?",new Object[]{name,location},new BeanPropertyRowMapper<>(Person.class));
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM person WHERE id=?",new Object[]{id});
    }

    @Override
    public int insert(Person person) {
        return jdbcTemplate.update("INSERT INTO PERSON (id,name,location,birth_date) VALUES(?,?,?,?)",new Object[]{person.getId(),person.getName(),person.getLocation(),person.getBirthDate()});
    }

    @Override
    public int update(Person person) {
        return jdbcTemplate.update("UPDATE PERSON  SET name=?,location=?,birth_date=? WHERE id=?",new Object[]{person.getName(),person.getLocation(),person.getBirthDate(),person.getId()});
    }
}
