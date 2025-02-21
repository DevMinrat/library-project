package com.devminrat.libraryProject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

@Entity
@Table(name = "Book")
public class Book {
    private static final int OVERDUE_DAYS = 10;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name length should be between 2 and 30")
    @Column
    private String name;

    @NotEmpty(message = "Author should not be empty")
    @Size(min = 2, max = 30, message = "Author length should be between 2 and 30")
    @Column
    private String author;

    @Min(value = 0, message = "Year should be greater than 0")
    @Column
    private int year;

    @ManyToOne
    @JoinColumn(name = "personid", referencedColumnName = "id")
    private Person owner;

    @Column(name = "checkout_date")
    private OffsetDateTime checkoutDate;

    @Transient
    private boolean overdue;

    public Book() {
    }

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public OffsetDateTime getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(OffsetDateTime checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public boolean isOverdue() {
        return getCheckoutDate() != null && getCheckoutDate().isBefore(OffsetDateTime.now().minusDays(OVERDUE_DAYS));
    }
}
