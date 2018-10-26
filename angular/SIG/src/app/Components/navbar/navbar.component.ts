import { AuthenticationService } from './../../Services/auth/authentication.service';
import { Component, OnInit } from '@angular/core';
// import { Router, Event, NavigationEnd } from '@angular/router';

@Component({
	selector: 'app-navbar',
	templateUrl: './navbar.component.html',
	styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

	constructor(private authenticationService: AuthenticationService, /*private router: Router*/) {
		// this.router.events.subscribe((event: Event) => {
		// 	if (event instanceof NavigationEnd) {
		// 		this.triggerlogged();
		// 	}
		// });
	}

	userName() {
		if (this.isLoggedIn()) {
			let currentUser = this.authenticationService.getCurrentUser();
			return `${currentUser.fname} ${currentUser.lname}`;
		} else {
			return 'Guest';
		}
	}

	isLoggedIn() {
		return this.authenticationService.isLoggedIn();
	}

	logout() {
		this.authenticationService.logout();
	}

	ngOnInit() {
	}

}
