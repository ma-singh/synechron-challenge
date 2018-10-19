import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { Router } from '@angular/router';

import { AppComponent } from './app.component';
import { BooksComponent } from './books/books.component';
import { FormComponent } from './books/form/form.component';
import { ModalComponent } from './modals/modal.component';

import { ModalService } from './modals/modal.service';

import { UiModule } from './ui/ui.module';
import { AppRoutingModule, routingComponents } from './app-routing.module';
import { TransactionsComponent } from './books/transactions/transactions.component';

@NgModule({
  declarations: [
    AppComponent,
    BooksComponent,
    FormComponent,
    ModalComponent,
    TransactionsComponent,
    routingComponents
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    UiModule
  ],
  providers: [ ModalService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
