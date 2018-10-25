
import { Balance } from './../Components/accounts/balance/balance';
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

<<<<<<< HEAD
  // this is completely out of wack! I chaneged to Balance after get, and to Balance in second line...
  getBalance() {
    return this.http.post<Balance>('http://localhost:8085/SIG/GetAccountTotalsServlet');
  }
=======
	// this is completely out of wack! I chaneged to Balance after get, and to Balance in second line...
	getBalance() {
		// return this.http.get<Balance[]>("????????????????");
	}
>>>>>>> 8b1d5c14fe6acac9de1e30bbdd7455f7b31ffe3d

}



