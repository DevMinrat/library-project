package com.devminrat.libraryProject.controllers;

import com.devminrat.libraryProject.dao.BookDAO;
import com.devminrat.libraryProject.dao.BookDAO;
import com.devminrat.libraryProject.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;

    @Autowired
    public BooksController(final BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String getBooks(final Model model) {
        model.addAttribute("books", bookDAO.getBooks());
        return "books/books";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") final Book book) {
        return "books/new";
    }

    @PostMapping
    public String saveBook(@ModelAttribute("book") final Book book) {
        bookDAO.createBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable final int id, Model model) {
        final Book book = bookDAO.getBook(id);
        model.addAttribute("book", book);
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") final Book book, @PathVariable final int id) {
        bookDAO.updateBook(id, book);
        return "redirect:/books";
    }


}
