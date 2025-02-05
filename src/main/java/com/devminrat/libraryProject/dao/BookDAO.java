package com.devminrat.libraryProject.dao;

import com.devminrat.libraryProject.models.Book;
import com.devminrat.libraryProject.models.Person;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BookDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Book> getBooks() {
        return sessionFactory.getCurrentSession().createQuery("from Book", Book.class).getResultList();
    }

    @Transactional
    public Book getBook(int id) {
        return sessionFactory.getCurrentSession().get(Book.class, id);
    }

    @Transactional
    public void createBook(Book book) {
        sessionFactory.getCurrentSession().persist(book);
    }

    @Transactional
    public void updateBook(Book book) {
        sessionFactory.getCurrentSession().merge(book);
    }

    @Transactional
    public void deleteBook(int id) {
        sessionFactory.getCurrentSession().remove(getBook(id));
    }

    @Transactional
    public void updateBookReader(int bookId, Integer personId) {
        Book book = getBook(bookId);
        if (personId == null) {
            book.setOwner(null);
        } else {
            Person person = sessionFactory.getCurrentSession().get(Person.class, personId);
            book.setOwner(person);
        }
    }
}
