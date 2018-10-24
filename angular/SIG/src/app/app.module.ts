import { appRoutes } from './routes';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { OrdersComponent } from './Components/orders/orders.component';
import { AccountsComponent } from './Components/accounts/accounts.component';
import { HomeComponent } from './Components/home/home.component';
import { BalanceService } from './Services/balance.service';



@NgModule({
  declarations: [
    AppComponent,
    OrdersComponent,
    AccountsComponent,
    HomeComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [
    BalanceService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
