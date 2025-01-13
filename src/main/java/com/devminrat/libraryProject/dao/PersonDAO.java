package com.devminrat.libraryProject.dao;

import com.devminrat.libraryProject.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPeople() {
        return jdbcTemplate.query("SELECT * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE person.id=?",
                        new BeanPropertyRowMapper<>(Person.class), id)
                .stream().findFirst().orElse(null);
    }

    public void createPerson(Person person) {
        jdbcTemplate.update("INSERT INTO person (fullname, birthday) values (?, ?)", person.getFullName(), person.getBirthday());
    }

    public void updatePerson(int id, Person person) {
        jdbcTemplate.update("UPDATE person SET fullname=?, birthday=? where id=?", person.getFullName(), person.getBirthday(), id);
    }

    public void deletePerson(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }
}
