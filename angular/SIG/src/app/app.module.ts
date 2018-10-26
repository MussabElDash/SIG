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
<<<<<<< HEAD
import { BalanceService } from './Services/balance.service';
import { HttpClientModule } from '@angular/common/http';
import { NewaccountComponent } from './Components/newaccount/newaccount.component';



@NgModule({
  declarations: [
    AppComponent,
    OrdersComponent,
    AccountsComponent,
    HomeComponent,
    NewaccountComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule
  ],
  providers: [
    BalanceService
  ],
  bootstrap: [AppComponent]
=======
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
		BalanceService
	],
<<<<<<< HEAD
	bootstrap: [AppComponent]
>>>>>>> 8b1d5c14fe6acac9de1e30bbdd7455f7b31ffe3d
=======
	bootstrap: [
		AppComponent,
	]
>>>>>>> 45203971426a2d87718069577aee3cbb57e7ad9a
})
export class AppModule { }
