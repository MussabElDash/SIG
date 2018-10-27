import { Router, ActivatedRoute } from '@angular/router';
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
  accountId:number;
  account:Account;
  securitys:Security[] = [];
  

  //securitys:Security[] = getAccountSecurities();

  constructor(private userService:UserService,
    private route: ActivatedRoute,
    private router: Router
    ) {
      this.route.params.subscribe(params => {
        if (params['acctId']){
          this.accountId = params['acctId'];
        }
      })
     }

  ngOnInit() {
    this.userService
    .getDetAcct(this.accountId)
    .subscribe( 
      data =>{
        this.account = data;
        this.securitys = data.securities;
      }, 
      error=>{ console.log("whoops")});
  }

  newOrder(){
    this.router.navigate(['orders', this.accountId]);
  }

}
