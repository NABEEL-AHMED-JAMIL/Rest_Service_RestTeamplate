package com.ballistic.rest.model;

import java.sql.Date;

/**
 * Created by Nabeel on 3/24/2017.
 */
public class Book {
    //
    private Long isbn;
    //
    private String author;
    //
    private String title;
    //
    private String publisher;
    //
    private String date;
    //


    public Book() {
        this.isbn = 0L;
    }


    public Book(Long isbn, String author, String title, String publisher, String date) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.publisher = publisher;
        this.date = date;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String  getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!isbn.equals(book.isbn)) return false;
        if (!author.equals(book.author)) return false;
        return title.equals(book.title);

    }

    @Override
    public int hashCode() {
        int result = isbn.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + title.hashCode();
        return result;
    }
}
