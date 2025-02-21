package com.devminrat.libraryProject.services;

import com.devminrat.libraryProject.models.Book;
import com.devminrat.libraryProject.models.Person;
import com.devminrat.libraryProject.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final PeopleService peopleService;

    @Autowired
    public BookService(BookRepository bookRepository, PeopleService peopleService) {
        this.bookRepository = bookRepository;
        this.peopleService = peopleService;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).getContent();
    }

    public List<Book> findByName(String name) {
        return bookRepository.findByNameStartingWith(name);
    }

    public Book findById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Book book) {
        book.setId(id);
        bookRepository.save(book);
    }

    @Transactional
    public void updateBookReader(int bookId, Integer personId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null) {
            if (personId == null) {
                book.setOwner(null);
                book.setCheckoutDate(null);
            } else {
                Person person = peopleService.findById(personId);
                book.setOwner(person);
                book.setCheckoutDate(OffsetDateTime.now());
            }
        }
    }


}
