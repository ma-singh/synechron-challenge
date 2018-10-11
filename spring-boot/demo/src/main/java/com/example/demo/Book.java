package com.example.demo;

import java.io.Serializable;
import java.util.ArrayList;

public class Book implements Serializable  {
	
	private static final long serialVersionUID = -7523045444068007618L;
	
	int bookID;
	String name;
	String author;
	String isbn;
	String publishDate;
	String category;
	int count;
	int issued;
	
	private int transCount = 0;
	
	ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	static class Transaction implements Serializable {
		
		private static final long serialVersionUID = 5649188228843612615L;
		
		int transactionID;
		String issueDate;
		String dueDate;
		String returnDate;
		
		public Transaction(int transactionID, String issueDate) {
			this.transactionID = transactionID;
			this.issueDate = issueDate;
			this.dueDate = issueDate + 30; //FIX THIS!
			this.returnDate = null;
		}
		
		public void returnBook(String returnDate){
			this.returnDate = returnDate;
		}

		public String getReturnDate() {
			return returnDate;
		}

		public int getTransactionID() {
			return transactionID;
		}

		public String getIssueDate() {
			return issueDate;
		}

		public String getDueDate() {
			return dueDate;
		}
		
	}
	
	public Book(int bookID, String name, String author, String isbn, String publishDate, String category,
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
	
	public void issue(String date){
		Transaction newTrans = new Transaction(transCount, date);
		transCount++;
		transactions.add(newTrans);
	}
	
	public void ret(int trans_id, String date){
		transactions.get(trans_id).returnBook(date);
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

	public String getPublishDate() {
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
	
	//REQUESTS
	//edit DONE
	//issue
	//return
	
	//add DONE
	//delete DONE
	
	//load books DONE
	//load transactions DONE
	
	

}
