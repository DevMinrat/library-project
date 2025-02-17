package com.devminrat.libraryProject.repositories;

import com.devminrat.libraryProject.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
