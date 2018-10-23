import { AuthenticationService } from './../authentication.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;

  

  constructor(
    private formBuilder: FormBuilder,
    private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
  });

  }

  get f() { return this.loginForm.controls; }

  onsubmit() {
    this.submitted = true;

    this.loading = true;
    this.authenticationService.login(this.f.username.value, this.f.password.value) 
    .subscribe(
      data => {
          //Route to login page
      },
      error => {
          //Error logging in
          this.loading = false;
      });
    

  }

}
