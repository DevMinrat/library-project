package com.devminrat.libraryProject.dao;

import com.devminrat.libraryProject.models.Person;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PersonDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Person> getPeople() {
        return sessionFactory.getCurrentSession().createQuery("from Person", Person.class).getResultList();
    }

    @Transactional
    public Person getPerson(int id) {
        Person person = sessionFactory.getCurrentSession().get(Person.class, id);
        Hibernate.initialize(person.getBooks());
        return person;
    }

    @Transactional
    public void createPerson(Person person) {
        sessionFactory.getCurrentSession().persist(person);
    }

    @Transactional
    public void updatePerson(int id, Person person) {
        sessionFactory.getCurrentSession().merge(person);
    }

    @Transactional
    public void deletePerson(int id) {
        sessionFactory.getCurrentSession().remove(getPerson(id));
    }
}
