package com.example.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.example.demo.Book.Transaction;

public class Model {
	
	private static final String filepath="src\\main\\resources\\lib";
	
	static class Library{
		
		public ArrayList<Book> catalog; // = new ArrayList<Book>();
		
		public Library() {
			this.catalog = ReadCatalog();
		}

		public ArrayList<Book> getCatalog() {
			return catalog;
		}
		
		public int getIterator() {
			return catalog.size();
		}
		
		public int addBook(Book b) {
			catalog.add(b);
			SaveToFile(catalog);
			return 1;
		}
		
		public int editBook(int id, int newCount){
			Book b = catalog.get(id);
			if(newCount >= b.getIssued()){
				b.edit(newCount);
				SaveToFile(catalog);
				return 1;
			}
			else{
				return 0; //Invalid Count
			}
		}
		
		public int deleteBook(int id){
			Book b = catalog.get(id);
			if(b.getIssued() > 0){
				return 0; //Copies still issued
			}
			else{
				catalog.set(id, null);
				SaveToFile(catalog);
				return 1;
			}
		}
		
		public int issueBook(int BookId, String date){
			Book b = catalog.get(BookId);
			if(b.getCount() > b.getIssued()){
				catalog.get(BookId).issue(date);
				SaveToFile(catalog);
				return 1;
			}
			else{
				return 0; //All copies issued
			}
		}	
		
		public int returnBook(int BookId, int TransId, String date){
			Transaction t = catalog.get(BookId).getTransactions().get(TransId);
			if(t.getReturnDate() == null){
				catalog.get(BookId).getTransactions().get(TransId).returnBook(date);
				SaveToFile(catalog);
				return 1;
			}
			else{
				return 0; //Already Returned
			}
		}	
	}
	
    public static void SaveToFile (Object save) {
        try {
        	FileOutputStream file = new FileOutputStream(filepath);
        	ObjectOutputStream stream = new ObjectOutputStream(file);
        	stream.writeObject(save);
        	stream.close();
        } catch (Exception ex) {
        	System.out.println("Unable to save!");
        	ex.printStackTrace();
        }
    }
   
    public static ArrayList<Book> ReadCatalog() {
    	try {
    		FileInputStream file = new FileInputStream(filepath);
    		ObjectInputStream stream = new ObjectInputStream(file);
    		@SuppressWarnings("unchecked")
			ArrayList<Book> lib = (ArrayList<Book>) stream.readObject(); 
    		stream.close();
    		return lib;
    	} catch (Exception ex) {
    		System.out.println("No saved catalog found!");
    		return new ArrayList<Book>();
    	}
    }

}
