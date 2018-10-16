package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicLong;

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
    	Book b1 = new Book((int) counter.incrementAndGet(), "Novel", "RP", "12345", "2008", "Sci-Fi", 5);
    	Book b2 = new Book((int) counter.incrementAndGet(), "Story", "PR", "67889", "2012", "Fantasy", 3);
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
    
    /*
    @RequestMapping("/test/edit")
    public int edit(@RequestParam(value="id", defaultValue="0") int id, 
    		@RequestParam(value="count", defaultValue="0") int count) {
    	int status = books.editBook(id,count);
        return status;
    }*/
    
    @RequestMapping("/books/edit")
    public int edit(@RequestBody ObjectNode json) {
    	int id = json.get("id").asInt();
    	int count = json.get("id").asInt();
    	int status = books.editBook(id,count);
        return status;
    }
    
    @RequestMapping("/books/delete")
    public int delete(@RequestParam(value="id", defaultValue="0") int id) {
    	int status = books.deleteBook(id);
        return status;
    }
    
    /*
    @RequestMapping("/test/add")
    public int books(@RequestParam(value="name", defaultValue="Unknown") String name,
    		@RequestParam(value="author", defaultValue="Unknown") String author,
    		@RequestParam(value="isbn", defaultValue="Unknown") String isbn,
    		@RequestParam(value="publishdate", defaultValue="Unknown") String publishDate,
    		@RequestParam(value="category", defaultValue="Unknown") String category,
    		@RequestParam(value="count", defaultValue="Unknown") int count) {
    	
    	Book book = new Book((int) counter.incrementAndGet(), name, author, isbn, publishDate, category, count);
    	books.addBook(book);
        return 1;
    }*/
    
    @RequestMapping("/books/add")
    public int add(@RequestBody ObjectNode json){ 
    	String name = json.get("name").asText();
    	String author = json.get("author").asText();
    	String isbn = json.get("isbn").asText();
    	String publishDate = json.get("publishDate").asText();
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
    
    @RequestMapping("/books/issue")
    public int delete(@RequestParam(value="id", defaultValue="0") int id,
    		@RequestParam(value="date", defaultValue="0") String date) {
    	int status = books.issueBook(id, date);
        return status;
    }
    
    @RequestMapping("/books/{id}/transacts/return")
    public int delete(@PathVariable("id") int bookId,
    		@RequestParam(value="id", defaultValue="0") int transId,
    		@RequestParam(value="date", defaultValue="0") String date) {
    	int status = books.returnBook(bookId, transId, date);
        return status;
    }
    
}
