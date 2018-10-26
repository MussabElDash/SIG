import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';

interface UserToken {
	user: object,
	token: string,
}

@Injectable({
	providedIn: 'root'
})
export class AuthenticationService {

	constructor(private http: HttpClient,
		private router: Router) { }

	httpOptions = {
		headers: new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' }),
		// withCredentials: true,
	};

	temp(token: string) {
		let options = {
			headers: new HttpHeaders({
				'Content-Type': 'application/x-www-form-urlencoded',
				'jwt-auth-token': token,
			}),
		}
		return this.http.post<any>('http://localhost:8085/SIG/ViewAccountServlet', {}, options)
			.pipe(map(user => {
				console.log(user);
				return user;
			}));
	}

	login(username: string, password: string) {

		let body = new HttpParams();
		// let headers = new HttpHeaders().set(
		// 	'Content-Type', 'application/x-www-form-urlencoded'
		// );

		body = body.set('username', username);
		body = body.set('password', password);

		//Have response return user with username and jwt token
		return this.http.post<UserToken>('http://localhost:8085/SIG/LoginServlet', body, this.httpOptions)
			.pipe(map(user => {

				// login successful if there's a jwt token in the response
				console.log(user.token);
				console.log(user.user);
				if (user) {
					// 	store user details and jwt token in local storage to keep user logged in between page refreshes
					localStorage.setItem('currentUser', JSON.stringify(user));
				}

				return user;
			}));
	}

	logout() {
		// remove user from local storage to log user out
		localStorage.removeItem('currentUser');
		this.router.navigate((['home']));
	}

	isLoggedIn() {
		let currentUser = this.getCurrentUser();
		if (currentUser) {
			return true;
		}
		return false;
	}

	getCurrentUser() {
		let stored = JSON.parse(localStorage.getItem('currentUser'));
		if (stored && stored.token) {
			return stored.user;
		}
	}
}
