import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from './../../Services/auth/authentication.service';

@Component({
	selector: 'app-home',
	templateUrl: './home.component.html',
	styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
	title = 'SIG';
	constructor(private authenticationService: AuthenticationService) { }

	ngOnInit() {
	}

	loggedIn() {
		return this.authenticationService.isLoggedIn();
	}

}
