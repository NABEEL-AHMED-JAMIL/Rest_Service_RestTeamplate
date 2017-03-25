package com.ballistic.rest.controller;

import com.ballistic.rest.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ballistic.rest.service.BookService;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by Nabeel on 3/25/2017.
 */
@RestController
public class BookRestController {
    // this help use to do process on the rest
    @Autowired
    BookService bookService;
    //

    //-------------------Retrieve All Book----------------
    @RequestMapping(value = "/books/" ,method = RequestMethod.GET)
    public ResponseEntity<List<Book>> listAllBooks() {
        System.out.println("-----------I---------------");
        System.out.println("-----------S---------------");
        System.out.println(".........This method is working or not this was check on the console base.......");
        List<Book> books = bookService.getAllBook();
        if(books.isEmpty()){

            return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
            //You many decide to return HttpStatus.NOT_FOUND
        }
        System.out.println("------------W--------------");
        System.out.println("------------O--------------");
        System.out.println("------------R--------------");
        System.out.println("------------K--------------");
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    //
    //-------------------Retrieve One Book----------------
    @RequestMapping(value = "/book/{isbn}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> getBook(@PathVariable("isbn") Long isbn) {
        System.out.println("-----------I---------------");
        System.out.println("-----------S---------------");
        System.out.println("Fetching Book with isbn " + isbn);
        Book book = bookService.findByIsbn(isbn);
        if (book == null) {
            System.out.println("Book with isbn " + isbn + " not found");
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
        System.out.println("------------W--------------");
        System.out.println("------------O--------------");
        System.out.println("------------R--------------");
        System.out.println("------------K--------------");
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    //
    //-------------------Create a New Book----------------
    @RequestMapping(value = "/book/", method = RequestMethod.POST)
    public ResponseEntity<Void> createBook(@RequestBody Book book, UriComponentsBuilder ucBuilder) {
        System.out.println("-----------I---------------");
        System.out.println("-----------S---------------");
        System.out.println("Creating book for " + book.getAuthor());

        if (bookService.isBookExist(book)) {
            System.out.println("-----------I---------------");
            System.out.println("-----------S---------------");
            System.out.println("A book with name " + book.getAuthor() + " already exist");
            System.out.println("------------W--------------");
            System.out.println("------------O--------------");
            System.out.println("------------R--------------");
            System.out.println("------------K--------------");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        bookService.saveBook(book);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/book/{isbn}").buildAndExpand(book.getIsbn()).toUri());
        System.out.println("A book with name " + book.getAuthor() + " Create");
        System.out.println("------------W--------------");
        System.out.println("------------O--------------");
        System.out.println("------------R--------------");
        System.out.println("------------K--------------");
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //
    //------------------- Update a book --------------------------------------------------------
    @RequestMapping(value = "/book/{isbn}", method = RequestMethod.PUT)
    public ResponseEntity<Book> updateBook(@PathVariable("isbn") long isbn, @RequestBody Book book) {
        System.out.println("-----------I---------------");
        System.out.println("-----------S---------------");
        System.out.println("Updating book " + isbn);
        Book currentBook = bookService.findByIsbn(isbn);

        if (currentBook==null) {
            System.out.println("book with isbn " + isbn + " not found");
            System.out.println("------------W--------------");
            System.out.println("------------O--------------");
            System.out.println("------------R--------------");
            System.out.println("------------K--------------");
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }

        currentBook.setAuthor(book.getAuthor());
        currentBook.setTitle(book.getTitle());
        currentBook.setPublisher(book.getPublisher());

        bookService.updateBook(currentBook);
        System.out.println("------------W--------------");
        System.out.println("------------O--------------");
        System.out.println("------------R--------------");
        System.out.println("------------K--------------");
        return new ResponseEntity<Book>(currentBook, HttpStatus.OK);
    }

    //------------------- Delete a book --------------------------------------------------------
    @RequestMapping(value = "/book/{isbn}", method = RequestMethod.DELETE)
    public ResponseEntity<Book> deleteBook(@PathVariable("isbn") long isbn) {
        System.out.println("-----------I---------------");
        System.out.println("-----------S---------------");
        System.out.println("Fetching & Deleting book with isbn " + isbn);

        Book book = bookService.findByIsbn(isbn);
        if (book == null) {
            System.out.println("Unable to delete. book with isbn " + isbn + " not found");
            System.out.println("------------W--------------");
            System.out.println("------------O--------------");
            System.out.println("------------R--------------");
            System.out.println("------------K--------------");
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }

        bookService.deleteBook(isbn);
        System.out.println("------------W--------------");
        System.out.println("------------O--------------");
        System.out.println("------------R--------------");
        System.out.println("------------K--------------");
        return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
    }


    //------------------- Delete All books --------------------------------------------------------

    @RequestMapping(value = "/books/", method = RequestMethod.DELETE)
    public ResponseEntity<Book> deleteAllbooks() {
        System.out.println("-----------I---------------");
        System.out.println("-----------S---------------");
        System.out.println("Deleting All books");
        bookService.deleteAllBook();
        System.out.println("------------W--------------");
        System.out.println("------------O--------------");
        System.out.println("------------R--------------");
        System.out.println("------------K--------------");
        return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
    }



}
