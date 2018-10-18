import { Component, OnInit } from '@angular/core';

import { Router, ActivatedRoute, ParamMap } from '@angular/router';

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
  public selectedId;
  private catalog: Array<Book> = [];
  private selectedBook;

 	constructor(
    private bookService: BookService,
    private modalService: ModalService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

 	ngOnInit() {
 		this.getBooks();
    this.route.paramMap.subscribe((params: ParamMap) => {
      let id = parseInt(params.get('id'));
      this.selectedId = id;
    });
 	}

  openModal(id: string) {
    this.modalService.open(id);
  }

  closeModal(id: string) {
    this.modalService.close(id);
  }

 	getBooks(): void {
 		this.bookService.getBooks()
    // set the catalog array to populate with the data from the API
    .subscribe(catalog => this.catalog = catalog);
 	}

  selectRow(selection) {
    console.log('Row ' + selection + ' selected.');
    console.log(this.catalog[selection]);
    // set selected book to chosen index in catalog array
    this.selectedBook = this.catalog[selection];
    // open the modal
    this.openModal('custom-modal-1');
  }

 	add(book: Object): void {
 		this.bookService.addBook(book as Book)
 			.subscribe(book => {
 				//this.catalog.push(book);
        //console.log('yay we added a book, but did we update our view?');
 			});
 	}

  delete(selectedBook) {
    this.bookService.deleteBook(selectedBook)
      .subscribe(
        this.catalog.splice(selectedBook, 1),
        console.log('Removed a book from the catalog');
      );
  }

  edit() {
    const targetId = this.selectedBook.bookID);
    const updatedCount = this.selectedBook.count);
    this.bookService.editBook(targetId, updatedCount)
      .subscribe(
        console.log('Edited Inventory for ' + this.selectedBook.name);
      );
    this.closeModal('custom-modal-1');
  }

  onSelect(book) {
    console.log(book.bookID);
    this.router.navigate([book.bookID], { relativeTo: this.route });
  }

  isSelected(book) {
    return book.bookID === this.selectedId;
  }

}
