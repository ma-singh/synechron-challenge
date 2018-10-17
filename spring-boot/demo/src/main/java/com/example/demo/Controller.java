package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

//import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Library;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class Controller {

    private final Library books = new Library();
    private final int count = books.getIterator();
    private final AtomicLong counter = new AtomicLong(count);
  
    @RequestMapping("/initialize")
    public ArrayList<Book> init() {
    	Date today = new Date();
    	Book b1 = new Book((int) counter.incrementAndGet(), "Novel", "RP", "12345", today, "Sci-Fi", 5);
    	Book b2 = new Book((int) counter.incrementAndGet(), "Story", "PR", "67889", today, "Fantasy", 3);
    	books.addBook(b1);
    	books.addBook(b2);
    	ArrayList<Book> set = books.getCatalog();
    	set.removeAll(Collections.singleton(null));
        return set;
    }
    
    @RequestMapping("/books")
    public ArrayList<Book> books() {
    	ArrayList<Book> set = books.getCatalog();
    	set.removeAll(Collections.singleton(null));
        return set;
    }
    
    @RequestMapping("/books/edit")
    public int edit(@RequestBody ObjectNode json) {
    	int id = json.get("id").asInt();
    	int count = json.get("count").asInt();
    	int status = books.editBook(id,count);
        return status;
    }
    
    @RequestMapping("/books/delete")
    public int delete(@RequestParam(value="id", defaultValue="0") int id) {
    	int status = books.deleteBook(id);
        return status;
    }
      
    @RequestMapping("/books/add")
    public int add(@RequestBody ObjectNode json) throws Exception{ 
    	String name = json.get("name").asText();
    	String author = json.get("author").asText();
    	String isbn = json.get("isbn").asText();
    	
    	String std = json.get("publishDate").asText();
    	Date publishDate = new SimpleDateFormat("yyyy-MM-dd").parse(std); 
    	
    	String category = json.get("category").asText();
    	int count = json.get("count").asInt();
    	
    	Book book = new Book((int) counter.incrementAndGet(), name, author, isbn, publishDate, category, count);
    	books.addBook(book);
        return 1;
    }
    
    @RequestMapping("/books/{id}/transacts")
    public Book books(@PathVariable("id") int id) {
    	ArrayList<Book> set = books.getCatalog();
        Book book = set.get(id);
        return book;
    }
    
    /*@RequestMapping("/books/issue")
    public int issue(@RequestParam(value="id", defaultValue="0") int id,
    		@RequestParam(value="date", defaultValue="0") @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
    	int status = books.issueBook(id, date);
        return status;
    }*/
    
    @RequestMapping("/books/issue")
    public int issue(@RequestBody ObjectNode json) throws Exception{
    	int id = json.get("id").asInt();
    	String std = json.get("issueDate").asText();
    	Date date = new SimpleDateFormat("yyyy-MM-dd").parse(std); 
    	int status = books.issueBook(id, date);
        return status;
    }
    
    /*@RequestMapping("/books/{id}/transacts/return")
    public int ret(@PathVariable("id") int bookId,
    		@RequestParam(value="id", defaultValue="0") int transId,
    		@RequestParam(value="date", defaultValue="0") @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
    	int status = books.returnBook(bookId, transId, date);
        return status;
    }*/
    
    @RequestMapping("/books/{id}/transacts/return")
    public int ret(@PathVariable("id") int bookId, @RequestBody ObjectNode json) throws Exception{
    	int transId = json.get("id").asInt();
    	String std = json.get("returnDate").asText();
    	Date date = new SimpleDateFormat("yyyy-MM-dd").parse(std);
    	int status = books.returnBook(bookId, transId, date);
        return status;
    }
    
}
