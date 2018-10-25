import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
	providedIn: 'root'
})
export class UserService {

	constructor(private http: HttpClient) { }

	register(username: string, pass: string, fname: string, lname: string, address: string,
		city: string, state: string, zip: number, ssn: number, dob: Date, phone: number) {

		let body = new HttpParams();
		let headers = new HttpHeaders().set(
			'Content-Type', 'application/x-www-form-urlencoded'
		);

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
			body,
			{ headers: headers });


	}



}
