package com.devminrat.libraryProject.controllers;

import com.devminrat.libraryProject.models.Book;
import com.devminrat.libraryProject.services.BookService;
import com.devminrat.libraryProject.services.PeopleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookService bookService;
    private final PeopleService peopleService;

    @Autowired
    public BooksController(final BookService bookService, final PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }

    @GetMapping
    public String getBooks(final Model model) {
        model.addAttribute("books", bookService.findAll());
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

        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable final int id, Model model) {
        final Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid final Book book, BindingResult bindingResult, @PathVariable final int id) {
        if (bindingResult.hasErrors())
            return "books/edit";

        bookService.update(id, book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable final int id, Model model) {
        final Book book = bookService.findById(id);

        if (book.getOwner() == null) {
            model.addAttribute("people", peopleService.findAll());
        } else {
            model.addAttribute("person", book.getOwner());
        }

        model.addAttribute("book", book);
        return "books/book";
    }

    @PostMapping("/{id}/freeBook")
    public String freeBook(@PathVariable final int id) {
        bookService.updateBookReader(id, null);
        return "redirect:/books/{id}";
    }

    @PostMapping("/{id}/setReader")
    public String setReader(@PathVariable final int id, @RequestParam int personId) {
        bookService.updateBookReader(id, personId);
        return "redirect:/books/{id}";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable final int id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
