import { Component, OnInit } from '@angular/core';

import { Book } from '../book';
import { BookService } from '../book.service';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {

	books: Book[];
  helloThere: string;

 	constructor(private bookService: BookService) { }

 	ngOnInit() {
 		this.getBooks();
    // this.beGreeted();
 	}

  // beGreeted(): void {
  //   this.bookService.getGreeting()
  //   .subscribe(greeting => this.helloThere = greeting);
  // }

 	getBooks(): void {
 		this.bookService.getBooks()
 		.subscribe(books => this.books = books);
 	}

 	add(book: Object): void {
 		this.bookService.addBook(book as Book)
 			.subscribe(book => {
        console.log(book);
 				this.books.push(book);
 			});
 	}

  // add(): void {
 	// 	this.bookService.addBook()
 	// 		.subscribe(book => {
  //       console.log(book);
 	// 		});
 	// }

 	delete(book: Book): void {
 		this.books = this.books.filter(h => h !== book);
 		this.bookService.deleteBook(book).subscribe();
 	}

}
