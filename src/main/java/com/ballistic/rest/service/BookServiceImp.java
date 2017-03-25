package com.ballistic.rest.service;

import com.ballistic.rest.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Nabeel on 3/24/2017.
 */
@Service("bookService")
@Transactional
// do all the process by the mysql database with out jpa
public class BookServiceImp implements BookService{

    //
    private static final AtomicLong counter = new AtomicLong();
    //
    private static List<Book> books;
    //
    static {
        //
        books = dummyBooks();
    }
    //
    public static List<Book> dummyBooks(){
        //
        try{
            //

            List<Book> books = new ArrayList<Book>();
            // adding few book inside the array
            books.add(new Book(counter.incrementAndGet() , "M.Mata" , "core java", "urdu bazar" , "1993-06-08" ));
            books.add(new Book(counter.incrementAndGet() , "Detail and Detail" , "core java", "urdu bazar" , "1991-06-07"));
            books.add(new Book(counter.incrementAndGet() , "M.hill" , "core java", "urdu bazar" , "1990-06-06"));
            return books;

        }catch (Exception e){
            System.out.println("Error in the List of book creating");
            return null;
        }

    }


    @Override
    public Book findByIsbn(Long isbn) {
        // ?
        try {

            for (Book book: books) {
                //
                if(book.getIsbn().equals(isbn)){
                    return book;
                }

            }
            return null;
        }catch (Exception e){
            System.out.println("Error in getting the book....");
            return  null;
        }
    }

    @Override
    public Book findByAuthor(String author) {
        //
        try {
            //

            for (Book book: books) {
                //
                if(book.getAuthor().equalsIgnoreCase(author)){
                    return book;
                }
            }
            return null;

        }catch (Exception e){
            System.out.println("Error in find by Author");
            return null;
        }

    }

    @Override
    public void saveBook(Book book) {
        //
        try {
            book.setIsbn(counter.incrementAndGet());
            books.add(book);
        }catch (Exception e){
            System.out.print("Error in Saveing the new Book");
            return;
        }

    }

    @Override
    public void updateBook(Book book) {
        //
        try {

            int index = books.indexOf(book);
            books.add(book);
        }catch (Exception e){
            System.out.print("Error in Update the Book");
            return;
        }

    }

    @Override
    public void deleteBook(Long isbn) {
        // iterator vs iterable ?
        // we can used direct the list properites but here we used iterator
       try{

           for(Iterator<Book> iterator = books.iterator(); iterator.hasNext();){
               //
               Book book = iterator.next();
               if(book.getIsbn() == isbn){
                   //
                   iterator.remove();
               }
           }
       }catch (Exception e){
           System.out.print("Error in Delete the Book");
           return;
       }


    }

    @Override
    public List<Book> getAllBook() {

        return books;
    }

    @Override
    public void deleteAllBook() {
        // remove all the data
        books.clear();

    }

    @Override
    public boolean isBookExist(Book book) {
        return findByAuthor(book.getAuthor())!= null;
    }
}
