import { Component, OnInit } from '@angular/core';

import { Book } from '../book';
import { BookService } from '../book.service';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {

	//books: Book[];
  private catalog: Array<Book> = [];

 	constructor(private bookService: BookService) { }

 	ngOnInit() {
 		this.getBooks();
 	}


 	getBooks(): void {
 		this.bookService.getBooks()
 		//.subscribe(books => this.books = books);
    .subscribe(books => this.catalog = books);
 	}

 	add(book: Object): void {
 		this.bookService.addBook(book as Book)
 			.subscribe(book => {
 				//this.catalog.push(book);
        console.log('yay we added a book, but did we update our view?');
 			});
 	}

 	delete(book: Book): void {
 		this.books = this.books.filter(h => h !== book);
 		this.bookService.deleteBook(book).subscribe();
 	}

}
