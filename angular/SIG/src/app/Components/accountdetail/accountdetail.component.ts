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

  account:Account;
  securitys:Security[] = [];
  

  //securitys:Security[] = getAccountSecurities();

  constructor(private userService:UserService) { }

  ngOnInit() {
    this.userService
    .getDetAcct()
    .subscribe( 
      data =>{
        //this.account = data;
      }, 
      error=>{ console.log("woops")});
  }

}
