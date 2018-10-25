import { HttpClientModule } from '@angular/common/http';
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

@NgModule({
	declarations: [
		AppComponent,
		LoginComponent,
		RegisterComponent,
		OrdersComponent,
		AccountsComponent,
		HomeComponent,
		AccountdetailComponent,
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
		BalanceService
	],
	bootstrap: [AppComponent]
})
export class AppModule { }
