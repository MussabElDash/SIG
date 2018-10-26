// import { AppComponent } from './app.component';
import { HomeComponent } from './Components/home/home.component';
import { AccountsComponent } from './Components/accounts/accounts.component';
import { RegisterComponent } from './Components/register/register.component';
import { LoginComponent } from './Components/login/login.component';
import { OrdersComponent } from './Components/orders/orders.component';
import { AccountdetailComponent } from './Components/accountdetail/accountdetail.component';
import { AddaccountComponent } from './Components/addaccount/addaccount.component';

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


const routes: Routes = [
<<<<<<< HEAD
// { path: '', component: HomeComponent },
{ path: 'login', component: LoginComponent },
{ path: 'accounts', component: AccountsComponent },
{ path: 'home', component: HomeComponent },
{ path: 'register', component: RegisterComponent },
{ path: 'orders', component: OrdersComponent },
{ path: 'account-details', component: AccountdetailComponent },
{ path: '**', redirectTo: 'home', pathMatch: 'full' },
=======
	// { path: '', component: HomeComponent },
	{ path: 'login', component: LoginComponent },
	{ path: 'accounts', component: AccountsComponent },
	{ path: 'home', component: HomeComponent },
	{ path: 'register', component: RegisterComponent },
	{ path: 'orders', component: OrdersComponent },
	{ path: 'account-details', component: AccountdetailComponent },
	{ path: 'add-account', component: AddaccountComponent},
	{ path: '**', redirectTo: 'home', pathMatch: 'full' },
>>>>>>> af5ff2e8416f429f92fca7acee819e3abb05c7d4
];

@NgModule({
imports: [RouterModule.forRoot(routes)],
exports: [RouterModule]
})


export class AppRoutingModule { }
