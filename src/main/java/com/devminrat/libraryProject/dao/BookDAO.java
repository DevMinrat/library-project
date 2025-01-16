package com.devminrat.libraryProject.dao;

import com.devminrat.libraryProject.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?", new BeanPropertyRowMapper<>(Book.class), id)
                .stream().findFirst().orElse(null);
    }

    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE personid=?", new BeanPropertyRowMapper<>(Book.class), id);
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

    public void updateBookReader(int id) {
        jdbcTemplate.update("UPDATE book SET personId=null WHERE id=?", id);
    }

    public void updateBookReader(int personId, int id) {
        jdbcTemplate.update("UPDATE book SET personId=? WHERE id=?", personId, id);
    }
}
