import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Injectable({
	providedIn: 'root'
})
export class BalanceService {
	// I need this service to return balance(s) from our DB.
	constructor(private http: HttpClient) { }

	httpOptions = {
		headers: new HttpHeaders({ 'Content-Type': 'application/json' })
	};

	// this is completely out of wack! I chaneged to Balance after get, and to Balance in second line...
	getBalance() {
		// return this.http.get<Balance[]>("????????????????");
	}

}
