import { map } from 'rxjs/operators';
import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

export class Balance {};

@Injectable({
	providedIn: 'root'
})
export class BalanceService {
	// I need this service to return balance(s) from our DB.
	constructor(private http: HttpClient,
				private router: Router) { }

	

	httpOptions = { 
		headers: new HttpHeaders({ 'Content-Type': 'application/json' })
	};

	// this is completely out of wack! I chaneged to Balance after get, and to Balance in second line...
	getBalance() {

		let currentUser = JSON.parse(localStorage.getItem('currentUser'));

		if (currentUser){

			return this.http.post<Balance>("http://localhost:8085/SIG/GetAccountTotalsServlet", currentUser.user)
			.pipe(map(amount => {
				console.log(amount);
				return amount;
			}));

		}else{
			this.router.navigate(['/login']);
		}
		// return this.http.get<Balance[]>("????????????????");
	}

}
