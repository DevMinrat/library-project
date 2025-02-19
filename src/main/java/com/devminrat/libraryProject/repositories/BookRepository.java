package com.devminrat.libraryProject.repositories;

import com.devminrat.libraryProject.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByNameStartingWith(String name);
}
