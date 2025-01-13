package com.devminrat.libraryProject.services;

import com.devminrat.libraryProject.dao.BookDAO;
import com.devminrat.libraryProject.dao.PersonDAO;
import com.devminrat.libraryProject.models.Book;
import com.devminrat.libraryProject.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    @Autowired
    public PersonService(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    public Person getPerson(int id) {
        Person person = personDAO.getPerson(id);
        List<Book> books = bookDAO.getBooksByPersonId(id);

        person.setBooks(books);
        return person;
    }
}
