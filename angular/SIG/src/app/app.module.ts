import { TokenInterceptor } from './_interceptors/token-interceptor';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';

import { BalanceService } from './Services/balance.service';

import { AppComponent } from './app.component';
import { LoginComponent } from './Components/login/login.component';
import { AccountsComponent } from './Components/accounts/accounts.component';
import { RegisterComponent } from './Components/register/register.component';
import { OrdersComponent } from './Components/orders/orders.component';
import { HomeComponent } from './Components/home/home.component';
import { AccountdetailComponent } from './Components/accountdetail/accountdetail.component';
import { NavbarComponent } from './Components/navbar/navbar.component';
import { WelcomeComponent } from './Components/welcome/welcome.component';

@NgModule({
	declarations: [
		AppComponent,
		LoginComponent,
		RegisterComponent,
		OrdersComponent,
		AccountsComponent,
		HomeComponent,
		AccountdetailComponent,
		NavbarComponent,
		WelcomeComponent,
	],
	imports: [
		BrowserModule,
		AppRoutingModule,
		ReactiveFormsModule,
		HttpClientModule,
		FormsModule,
		ReactiveFormsModule,
	],
	providers: [
		BalanceService,
		{ provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true }
	],
	bootstrap: [
		AppComponent,
	]
})
export class AppModule { }
