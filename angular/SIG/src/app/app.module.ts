import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AccountsSummaryComponent } from './Components/accounts-summary/accounts-summary.component';
import { OrdersComponent } from './Components/orders/orders.component';


@NgModule({
  declarations: [
    AppComponent,
    AccountsSummaryComponent,
    OrdersComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
