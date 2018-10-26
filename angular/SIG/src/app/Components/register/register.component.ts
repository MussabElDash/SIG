import { Router } from '@angular/router';
import { UserService } from './../../Services/user/user.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
	selector: 'app-register',
	templateUrl: './register.component.html',
	styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
	registerForm: FormGroup;
	loading = false;
	submitted = false;

	constructor(
		private formBuilder: FormBuilder,
		private userService: UserService,
		private router: Router) { }

	ngOnInit() {
		this.registerForm = this.formBuilder.group({
			username: ['', Validators.required],
			password1: ['', Validators.required],
			password2: ['', Validators.required],
			fname: ['', Validators.required],
			lname: ['', Validators.required],
			address: ['', Validators.required],
			city: ['', Validators.required],
			state: ['', Validators.required],
			zip: ['', Validators.required],
			ssn: ['', Validators.required],
			dob: ['', Validators.required],
			phone: ['', Validators.required]


		});
	}

	get f() { return this.registerForm.controls; }

	onSubmit() {
		this.submitted = true;

		this.loading = true;
		this.userService.register(this.f.username.value, this.f.password1.value,
			this.f.fname.value, this.f.lname.value, this.f.address.value, this.f.city.value,
			this.f.state.value, this.f.zip.value, this.f.ssn.value, this.f.dob.value,
			this.f.phone.value)
			.subscribe(
				data => {
					//Route to login page
					this.router.navigate(['/login']);
				},
				error => {
					//Error logging in
					this.loading = false;
				});

	}
}
