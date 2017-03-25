package com.ballistic.rest.service;

import com.ballistic.rest.model.Book;

import java.util.List;

/**
 * Created by Nabeel on 3/24/2017.
 */
public interface BookService {
    //
    Book findByIsbn(Long isbn);
    //
    Book findByAuthor(String author);
    //
    void saveBook(Book book);
    //
    void updateBook(Book book);
    //
    void deleteBook(Long isbn);
    //
    List<Book> getAllBook();
    //
    void deleteAllBook();
    //
    public boolean isBookExist(Book book);
}
