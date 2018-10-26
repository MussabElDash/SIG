import { AuthenticationService } from './../../Services/auth/authentication.service';
import { Component, OnInit } from '@angular/core';

@Component({
	selector: 'app-navbar',
	templateUrl: './navbar.component.html',
	styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

	loggedIn = false;
	userName = 'Guest';
	constructor(private authenticationService: AuthenticationService) {
		if (this.authenticationService.isLoggedIn()) {
			this.loggedIn = true;
			let currentUser = this.authenticationService.getCurrentUser();
			this.userName = `${currentUser.fname} ${currentUser.lname}`
		}
	}

	ngOnInit() {
	}

}
