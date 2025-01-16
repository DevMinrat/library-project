package com.devminrat.libraryProject.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name length should be between 2 and 30")
    private String name;

    @NotEmpty(message = "Author should not be empty")
    @Size(min = 2, max = 30, message = "Author length should be between 2 and 30")
    private String author;

    @Min(value = 0, message = "Year should be greater than 0")
    private int year;

    @Min(value = 0, message = "Pages should be greater than 0")
    private int pages;
    private Integer personId;

    public Book() {
    }

    public Book(int id, String name, String author, int year, int pages) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.pages = pages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }
}
