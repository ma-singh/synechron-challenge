import { Component, OnInit } from '@angular/core';

import { Book } from '../book';
import { BookService } from '../book.service';
import { ModalService } from '../modals/modal.service';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {

	//books: Book[];
  private catalog: Array<Book> = [];

 	constructor(
    private bookService: BookService,
    private modalService: ModalService
  ) { }

 	ngOnInit() {
 		this.getBooks();
 	}

  openModal(id: string) {
    this.modalService.open(id);
  }

  closeModal(id: string) {
    this.modalService.close(id);
  }

 	getBooks(): void {
 		this.bookService.getBooks()
 		//.subscribe(books => this.books = books);
    .subscribe(catalog => this.catalog = catalog);
 	}

 	add(book: Object): void {
 		this.bookService.addBook(book as Book)
 			.subscribe(book => {
 				//this.catalog.push(book);
        console.log('yay we added a book, but did we update our view?');
 			});
 	}

 	// delete(book: Book): void {
 	// 	this.catalog = this.catalog.filter(h => h !== book);
 	// 	this.bookService.deleteBook(book).subscribe();
 	// }

  selectRow(selection) {
    console.log('Row ' + selection + ' selected.');
    console.log(this.catalog[selection]);

    //this.delete(selection);
    this.openModal('custom-modal-1');
  }

  delete(selectedBook) {
    this.bookService.deleteBook(selectedBook)
      .subscribe(
        this.catalog.splice(selectedBook, 1),
        console.log('Removed a book from the catalog');
      )
  }

}
