import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  cancel() {
    this.closeForm();
  }

  closeForm() {
    // send null to 'outlet' to clear it's contents and return back to the provided route
    this.router.navigate([{ outlets: { popup: null }}]);
  }

}
