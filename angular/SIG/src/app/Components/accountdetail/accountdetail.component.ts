import { Account } from './../../_models/account';
import { HttpHeaders } from '@angular/common/http';
import { UserService } from './../../Services/user/user.service';
import { Security } from './../../_models/security';

import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-accountdetail',
  templateUrl: './accountdetail.component.html',
  styleUrls: ['./accountdetail.component.css']
})
export class AccountdetailComponent implements OnInit {

  accounts:Account[] = [];
  securitys:Security[] = [];
  

  //securitys:Security[] = getAccountSecurities();

  constructor(private userService:UserService) { }

  ngOnInit() {
    this.userService
    .getAcctService()
    .subscribe( 
      data =>{
        this.accounts = data;
      }, 
      error=>{ console.log("woops")});
  }

}
