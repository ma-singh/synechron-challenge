import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Book } from './book';

const httpOptions = {
	headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

@Injectable({ providedIn: 'root' })
export class BookService {
	private booksUrl = 'http://localhost:8080/books';

	constructor(
		private http: HttpClient
	) { }

	// getGreeting (): Observable<any> {
	// 	return this.http.get('http://localhost:8080/greeting?name=kenobi')
	// 		.pipe(
	// 			tap(greeting => console.log('i\'ve been greeted')),
	// 			catchError(this.handleError('getGreeting'))
	// 		);
	// }

	getBooks (): Observable<Book[]> {
		return this.http.get<Book[]>(this.booksUrl)
			.pipe(
				tap(book => console.log('fetched books')),
				catchError(this.handleError('getBooks', []))
			);
	}

	addBook(book : Book): Observable<any> {
		console.log(book);
		return this.http.post<Book>('http://localhost:8080/books/add', book, httpOptions)
			.pipe(
				tap((book: Book) => console.log('POST successful', book)),
				catchError(this.handleError<Book>('addBook'))
			);
	}

	deleteBook(book: Book): Observable<Book> {
		const catalogUrl = 'http://localhost:8080/delete';
		const target = 1;

		return this.http.delete<Book>('http://localhost:8080/books/delete?id=1', httpOptions)
			.pipe(
				tap(_ => console.log(`Deleted hero at index: ${target}`)),
				catchError(this.handleError<Book>('deleteHero'))
			);
	}

	private handleError<T> (operation = 'operation', result?:T) {
		return (error: any): Observable<T> => {
			console.error(error);

			return of(result as T);
		};
	}
}
