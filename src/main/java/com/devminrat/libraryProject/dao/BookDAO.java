package com.devminrat.libraryProject.dao;

import com.devminrat.libraryProject.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks() {
        return jdbcTemplate.query("SELECT * from book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book getBook(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findFirst().orElse(null);
    }

    public void createBook(Book book) {
        jdbcTemplate.update("INSERT INTO book (name, author, year) values (?, ?, ?)", book.getName(), book.getAuthor(), book.getYear());
    }

    public void updateBook(int id, Book book) {
        jdbcTemplate.update("UPDATE book SET name=?, author=?, year=? where id=?", book.getName(), book.getAuthor(), book.getYear(), id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }
}
