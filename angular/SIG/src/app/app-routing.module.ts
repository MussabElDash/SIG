import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AccountsSummaryComponent } from './Components/accounts-summary/accounts-summary.component';
import { OrdersComponent } from './Components/orders/orders.component';



const routes: Routes = [
  {
    path: '',

  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
