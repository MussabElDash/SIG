import { appRoutes } from './routes';
import { HttpClientModule } from '@angular/common/http'; import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AccountdetailComponent } from './accountdetail/accountdetail.component';
import { RegisterComponent } from './register/register.component';

@NgModule({
	declarations: [
		AppComponent,
		LoginComponent,
		AccountdetailComponent,
		RegisterComponent,
		OrdersComponent,
		AccountsComponent,
		HomeComponent,
	],
	imports: [
		BrowserModule,
		AppRoutingModule,
		ReactiveFormsModule,
		HttpClientModule,
		RouterModule.forRoot(appRoutes)],
	providers: [
		BalanceService
	],
	bootstrap: [AppComponent]
})
export class AppModule { }
