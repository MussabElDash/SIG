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
  // I need balance
  balance:Balance;
  accounts:Account[];
  constructor(private balanceService: BalanceService,
    private userService: UserService) {

  }

  ngOnInit() {
    this.balanceService.getBalance()
    .subscribe(
      data =>{
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

}
