import { AuthenticationService } from './../auth/authentication.service';
import { Account } from './../../_models/account';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
	providedIn: 'root'
})
export class UserService {

	constructor(private http: HttpClient,
		private authService:AuthenticationService,
		private router: Router) { }

	register(username: string, pass: string, fname: string, lname: string, address: string,
		city: string, state: string, zip: number, ssn: number, dob: Date, phone: number) {

		const httpOptions = {
			headers: new HttpHeaders({
				'Content-Type': 'application/x-www-form-urlencoded'
			}),
		};

		let body = new HttpParams();
		body = body.set('username', username);
		body = body.set('pass', pass);
		body = body.set('fname', fname);
		body = body.set('lname', lname);
		body = body.set('address', address);
		body = body.set('city', city);
		body = body.set('state', state);
		body = body.set('zip', zip.toString());
		body = body.set('ssn', ssn.toString());
		body = body.set('dob', dob.toString());
		body = body.set('phone', phone.toString());

		return this.http.post("http://localhost:8085/SIG/RegistrationServlet",
			body, httpOptions);

	}

	getAcctService(){

		// const httpOptions = {
		// 	headers: new HttpHeaders({
		// 		'Content-Type': 'application/x-www-form-urlencoded'
		// 	}),
		// };

		let currentUser = this.authService.getCurrentUser;

		if(currentUser){

			return this.http.get<Account[]>("http://localhost:8085/SIG/GetUserAccountsServlet")
			.pipe(map(userAccounts => {
				console.log(userAccounts);
				return userAccounts;
			}));

		}else{
			this.router.navigate((['login']));
		}

		
	}

	getDetAcct(accountId:number){

		const httpOptions = {
			headers: new HttpHeaders({
				'Content-Type': 'application/x-www-form-urlencoded'
			}),
		};

		let body = new HttpParams();
		body = body.set('aid', accountId.toString());

		return this.http.post<Account>("http://localhost:8085/SIG/ViewAccountServlet", body)
		.pipe(map(account => {
			console.log(account);
			return account;
		}))
	}

	addAccount(accountName: string, accountType: string){
		const httpOptions = {
			headers: new HttpHeaders({
				'Content-Type': 'application/x-www-form-urlencoded'
			}),
		};

		let body = new HttpParams();
		body = body.set('accountName', accountName);
		body = body.set('accountType', accountType);


		return this.http.post("http://localhost:8085/SIG/CreateAccountServlet",
			body, httpOptions);

	}



}
