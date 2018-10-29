import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';

// interface UserToken {
// 	user: object,
// 	token: string,
// }

@Injectable({
	providedIn: 'root'
})
export class TokenInterceptor implements HttpInterceptor {
	constructor(private router: Router) { }
	intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

		console.log("interceptor activated");
		// add authorization header with jwt token if available
		let currentUser = JSON.parse(localStorage.getItem('currentUser'));
		// let url = "http://localhost:8085/SIG/";
		let url = "http://3.16.36.61:8085/SIG/";
		request = request.clone({
			url: url + request.url
		});


		if (currentUser && currentUser.token) {

			// let options = {
			//     headers: new HttpHeaders({
			//         'Content-Type': 'application/x-www-form-urlencoded',
			//         'jwt-auth-token': currentUser.token,
			//     }),
			// }

			request = request.clone({
				setHeaders: {
					'Content-Type': 'application/x-www-form-urlencoded',
					'jwt-auth-token': currentUser.token
				}
			});

		} else {
			console.log("no token found");
		}

		return next.handle(request).pipe(catchError(
			(error: any, ) => {
				if (error.status === 401) {
					this.router.navigate(['home']);
				}
				throw error;
			}));
	}
}
