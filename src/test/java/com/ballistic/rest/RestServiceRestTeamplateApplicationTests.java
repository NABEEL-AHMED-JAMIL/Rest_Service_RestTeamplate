package com.ballistic.rest;

import com.ballistic.rest.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.ballistic.rest.service.BookServiceImp.dummyBooks;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestServiceRestTeamplateApplicationTests {

	//
	public static final String REST_SERVICE_URI = "http://localhost:8080";
  	public RestTemplate restTemplate;
	public List<Book> books = new ArrayList<Book>();
	public List<Book> dbBook = dummyBooks();

	private RestTemplate getRestTemplate(){
		return  new RestTemplate();
	}

	/* GET */
	@Test
	public void getAllBooks() {
		//
		try {
			//
			System.out.println("Testing....fecth all the Books");
			restTemplate = getRestTemplate();
			//
			List<LinkedHashMap<String, Object>> booksMap = restTemplate.getForObject(REST_SERVICE_URI+"/books/", List.class);

			if(booksMap != null){
				for(LinkedHashMap<String, Object> map : booksMap){
					System.out.println("------------------------------------------------------------------------------------------------------------");
					Book book = new Book();
					book.setIsbn(Long.parseLong(map.get("isbn").toString()));
					book.setAuthor(map.get("author").toString());
					book.setTitle(map.get("title").toString());
					book.setPublisher(map.get("publisher").toString());
					book.setDate(map.get("date").toString());
					System.out.println("Book : Isbn = "+map.get("isbn")+", Author = "+map.get("author")+", Title = "+map.get("title")+", Publisher = "+map.get("publisher"));
					books.add(book);
					System.out.println("------------------------------------------------------------------------------------------------------------");
				}
				// know compare
				assertEquals(books.get(0).getAuthor(),dbBook.get(0).getAuthor());
			}else{
				System.out.println("------------No Book exist----------");
			}

		}catch (Exception e){
			System.out.print("Getting all the books test Failed");
		}
	}


	/* GET */
	@Test
	public void getBook(){

		try {

			System.out.println("----------Testing getBook----------");
			restTemplate = getRestTemplate();
			Book book = restTemplate.getForObject(REST_SERVICE_URI+"/book/1", Book.class);
			System.out.println("------------------------------------------------------------------------------------------------------------");
			System.out.println(book);
			System.out.println("------------------------------------------------------------------------------------------------------------");
			// know compare
			assertEquals(book.getAuthor(),dbBook.get(0).getAuthor());

		}catch (Exception e){
			//
			System.out.print("Error in getting the book....");

		}


	}

	/* POST */
	@Test
	public void createBook() {
		//
		try {
			//
			System.out.println("------Testing Create Book----------");
			restTemplate = getRestTemplate();
			Book book = new Book();
			book.setTitle("Data Structures and Algorithms Made Easy");
			book.setPublisher("2011");
			book.setDate("2-6-2011");
			book.setAuthor("Narasimha");
			//
			URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/book/", book, Book.class);
			//
			System.out.println("------------------------------------------------------------------------------------------------------------");
			System.out.println("Location : "+uri.toASCIIString());
			Long isbn  = Long.parseLong(uri.getPath().toString().substring(uri.getPath().lastIndexOf('/')+1));
			System.out.println("Location : "+isbn);
			System.out.println("------------------------------------------------------------------------------------------------------------");
			//
			Book getbook = restTemplate.getForObject(REST_SERVICE_URI+"/book/"+isbn, Book.class);
			System.out.println("------------------------------------------------------------------------------------------------------------");
			System.out.println(getbook);
			System.out.println("------------------------------------------------------------------------------------------------------------");
			// know compare
			assertEquals(book.getAuthor(),getbook.getAuthor());

		}catch (Exception e){
			System.out.print("Error in Createing the new book");
		}
	}

	/* PUT */
	@Test
	public void updateBook() {
		//
		try {
			//
			System.out.println("---------Testing update Book API----------");
			restTemplate = getRestTemplate();
			Book book  = new Book(1L,"Data Structures","Narasimha","25-52-65","45");
			restTemplate.put(REST_SERVICE_URI+"/book/"+book.getIsbn(), book);
			//
			System.out.println("------------------------------------------------------------------------------------------------------------");
			System.out.println(book);
			System.out.println("------------------------------------------------------------------------------------------------------------");
			// book is getting after the save
			Book getbook = restTemplate.getForObject(REST_SERVICE_URI+"/book/"+book.getIsbn(), Book.class);
			System.out.println("------------------------------------------------------------------------------------------------------------");
			System.out.println(getbook);
			System.out.println("------------------------------------------------------------------------------------------------------------");
			assertEquals(getbook , book);

		}catch (Exception e){
			System.out.print("Error in update the book");
		}

	}

	/* DELETE */
	@Test
	public void deleteBook() {
		System.out.println("---------Testing delete Book----------");
		restTemplate = getRestTemplate();
		restTemplate.delete(REST_SERVICE_URI+"/book/3");
	}


	/* DELETE */
	@Test
	public void deleteAllBooks() {

		System.out.println("--------Testing all delete Books----------");
		restTemplate = getRestTemplate();
		restTemplate.delete(REST_SERVICE_URI+"/books/");

	}

}
