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
	// { path: '', component: HomeComponent },
	{ path: 'login', component: LoginComponent },
	{ path: 'accounts', component: AccountsComponent },
	{ path: 'home', component: HomeComponent },
	{ path: 'register', component: RegisterComponent },
	{ path: 'account-details/:acctId', component: AccountdetailComponent },
	{ path: 'orders/:acctId', component: OrdersComponent},
	{ path: 'add-account', component: AddaccountComponent},
	{ path: '**', redirectTo: 'home', pathMatch: 'full' },
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})


export class AppRoutingModule { }
