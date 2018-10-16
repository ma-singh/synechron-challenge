import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Book } from '../../book';
import { BooksComponent } from '../books.component';

@Component({
  providers: [
    BooksComponent
  ],
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  bookModel = new Book();

  constructor(
    private router: Router,
    private catalog: BooksComponent;
  ) { }

  ngOnInit() {
  }

  onSubmit() {
    // concat the author's name properly, then delete the fields
    this.bookModel.author = this.bookModel.authorLast + ", " + this.bookModel.authorFirst;
    delete this.bookModel.authorLast;
    delete this.bookModel.authorFirst;

    // make sure you call getBooks first so you can properly add to the local array before you access the API
    this.catalog.getBooks();
    this.catalog.catalog.push(this.bookModel);
    this.catalog.add(this.bookModel);

    // close the form and change the url back to default path
    this.closeForm();
    this.router.navigateByUrl('/');
  }

  cancel() {
    this.closeForm();
  }

  closeForm() {
    // send null to 'outlet' to clear it's contents and return back to the provided route
    this.router.navigate([{ outlets: { popup: null }}]);
  }

}
