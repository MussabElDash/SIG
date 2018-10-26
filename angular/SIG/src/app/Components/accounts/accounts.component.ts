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
  constructor(private balanceService: BalanceService) {

  }

  ngOnInit() {
    this.balanceService.getBalance()
    .subscribe(
      data =>{
        this.balance = data;
      }

    )
  }

}
