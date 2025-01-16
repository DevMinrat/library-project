package com.devminrat.libraryProject.controllers;

import com.devminrat.libraryProject.dao.BookDAO;
import com.devminrat.libraryProject.dao.PersonDAO;
import com.devminrat.libraryProject.models.Book;
import com.devminrat.libraryProject.models.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(final BookDAO bookDAO, final PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
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
    public String saveBook(@ModelAttribute("book") @Valid final Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";

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
    public String updateBook(@ModelAttribute("book") @Valid final Book book, BindingResult bindingResult, @PathVariable final int id) {
        if (bindingResult.hasErrors())
            return "books/edit";

        bookDAO.updateBook(id, book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable final int id, Model model) {
        final Book book = bookDAO.getBook(id);

        if (book.getPersonId() == null) {
            model.addAttribute("people", personDAO.getPeople());
        } else {
            Person person = personDAO.getPerson(book.getPersonId());
            model.addAttribute("person", person);
        }

        model.addAttribute("book", book);
        return "books/book";
    }

    @PostMapping("/{id}/freeBook")
    public String freeBook(@PathVariable final int id) {
        bookDAO.updateBookReader(id);
        return "redirect:/books/{id}";
    }

    @PostMapping("/{id}/setReader")
    public String setReader(@PathVariable final int id, @RequestParam int personId) {
        bookDAO.updateBookReader(personId, id);
        return "redirect:/books/{id}";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable final int id) {
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }
}
