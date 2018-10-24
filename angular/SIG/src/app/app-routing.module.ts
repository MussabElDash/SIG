import { AccountdetailComponent } from './accountdetail/accountdetail.component';
import { LoginComponent } from './login/login.component';
import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'accountdetail', component: AccountdetailComponent}
];

@NgModule({
  exports: [ RouterModule ]
})


export class AppRoutingModule {}
