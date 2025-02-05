package com.devminrat.libraryProject.services;

import com.devminrat.libraryProject.dao.BookDAO;
import com.devminrat.libraryProject.dao.PersonDAO;
import com.devminrat.libraryProject.models.Book;
import com.devminrat.libraryProject.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonDAO personDAO;

    @Autowired
    public PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public Person getPerson(int id) {
        return personDAO.getPerson(id);
    }
}
