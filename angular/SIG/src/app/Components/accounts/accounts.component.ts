import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from './../../Services/user/user.service';
import { Account } from './../../_models/account';
import { BalanceService } from './../../Services/balance.service';
import { Component, OnInit } from '@angular/core';

export class Balance {};

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {
  acctForm: FormGroup;
  // I need balance
  balance:Balance;
  accounts:Account[];
  constructor(private balanceService: BalanceService,
    private userService: UserService,
    private router: Router) {

   }

  ngOnInit() {
    this.balanceService.getBalance()
     .subscribe(
      data => {
        this.balance = data;
      }

    )

    this.userService.getAcctService()
      .subscribe(
        data =>{
          this.accounts = data;
        }
      )
    
  }

  viewAccount( accountId:number ){

    this.router.navigate(['accout-details']);
  }

  addAccount(){
    this.router.navigate(['add-account']);
  }

}
