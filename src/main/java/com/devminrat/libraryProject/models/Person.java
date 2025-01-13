package com.devminrat.libraryProject.models;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private int id;
    private String fullName;
    private int birthday;
    private List<Book> books = new ArrayList<>();

    public Person() {
    }

    public Person(int id, String fullName, int birthday) {
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
