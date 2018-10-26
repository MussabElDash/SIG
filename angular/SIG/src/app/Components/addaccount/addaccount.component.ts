import { Router } from '@angular/router';
import { UserService } from './../../Services/user/user.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
	selector: 'app-addaccount',
	templateUrl: './addaccount.component.html',
	styleUrls: ['./addaccount.component.css']
})
export class AddaccountComponent implements OnInit {
	addaccountForm: FormGroup;
	loading = false;
	submitted = false;

	constructor(
		private formBuilder: FormBuilder,
		private userService: UserService,
		private router: Router) { }

	ngOnInit() {
		this.addaccountForm = this.formBuilder.group({
			accountName: ['', Validators.required],
			accountType: ['', Validators.required],
		});
	}

	get f() { return this.addaccountForm.controls; }

	onSubmit() {
		this.submitted = true;

		this.loading = true;
		this.userService.addAccount(this.f.accountName.value, this.f.accountType.value)
			.subscribe(
				data => {
					//Route to login page
					this.router.navigate(['/accounts']);
				},
				error => {
					//Error logging in
					this.loading = false;
				});

	}
}
