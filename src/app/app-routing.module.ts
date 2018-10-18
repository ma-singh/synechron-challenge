import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { FormComponent } from './books/form/form.component';
import { TransactionsComponent } from './books/transactions/transactions.component';

import { PreloadService } from './preload.service';

const appRoutes: Routes = [
  {
    path: 'add',
    component: FormComponent,
    outlet: 'popup'
  },
  {
    path: ':id',
    component: TransactionsComponent
  },
  {
    path: '',
    redirectTo: '/',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      appRoutes,
      {
        enableTracing: false,
        preloadingStrategy: PreloadService,
      }
    )
  ],
  exports: [
    RouterModule
  ]
})

export class AppRoutingModule { }
export const routingComponents = [ TransactionsComponent ]
