package com.example.demo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Book implements Serializable  {
	
	private static final long serialVersionUID = -7523045444068007618L;
	
	int bookID;
	String name;
	String author;
	String isbn;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	Date publishDate;
	
	String category;
	int count;
	int issued;
	
	private int transCount = 0;
	
	ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	static class Transaction implements Serializable {
		
		private static final long serialVersionUID = 5649188228843612615L;
		
		int transactionID;
		
		@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
		Date issueDate;
		
		@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
		Date dueDate;
		
		@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
		Date returnDate;
		
		public Transaction(int transactionID, Date issueDate) {
			this.transactionID = transactionID;
			this.issueDate = issueDate;
			this.dueDate = nextMonth(issueDate); //FIX THIS!
			this.returnDate = null;
		}
		
		public void returnBook(Date returnDate){
			this.returnDate = returnDate;
		}

		public Date getReturnDate() {
			return returnDate;
		}

		public int getTransactionID() {
			return transactionID;
		}

		public Date getIssueDate() {
			return issueDate;
		}

		public Date getDueDate() {
			return dueDate;
		}
		
	}
	
	public Book(int bookID, String name, String author, String isbn, Date publishDate, String category,
			int count) {
		this.bookID = bookID-1;
		this.name = name;
		this.author = author;
		this.isbn = isbn;
		this.publishDate = publishDate;
		this.category = category;
		this.count = count;
		this.issued = 0;
	}
	
	public void edit(int count){
		this.count = count;
	}
	
	public void issue(Date date){
		Transaction newTrans = new Transaction(transCount, date);
		issued++;
		transCount++;
		transactions.add(newTrans);
	}
	
	public void ret(int trans_id, Date date){
		transactions.get(trans_id).returnBook(date);
		issued--;
	}

	public int getBookID() {
		return bookID;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}

	public String getIsbn() {
		return isbn;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public String getCategory() {
		return category;
	}

	public int getCount() {
		return count;
	}

	public int getIssued() {
		return issued;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
	
	public static Date nextMonth(Date issue) {
        Calendar dates = Calendar.getInstance();
        dates.setTime(issue);
        dates.add(Calendar.MONTH, 1);
        return dates.getTime();
    }
	
	//REQUESTS
	//edit DONE
	//issue
	//return
	
	//add DONE
	//delete DONE
	
	//load books DONE
	//load transactions DONE
	
	

}
