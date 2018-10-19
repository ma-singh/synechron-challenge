import { Component, OnInit } from '@angular/core';

import { Router, ActivatedRoute, ParamMap } from '@angular/router';

import { BooksComponent } from '../books.component';

@Component({
  providers: [
    BooksComponent
  ],
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {

  public selectedId;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private catalog: BooksComponent
  ) { }

  ngOnInit() {
    this.route.paramMap.subscribe((params: ParamMap) => {
      let id = parseInt(params.get('id'));
      this.selectedId = id;
    });
    // get all the books in the catalog
    this.catalog.getBooks();
  }

  onSelect(item) {
    this.router.navigate([item.id], { relativeTo: this.route });
  }

  isSelected(item) {
    return item.id === this.selectedId;
  }

  goBack() {
    this.router.navigateByUrl('/');
  }

}
