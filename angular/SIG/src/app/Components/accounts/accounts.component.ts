import { Balance } from './balance/balance';
import { BalanceService } from '../../Services/balance/balance.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {
  balance: Balance;
  // I need balance
  constructor(private balanceService: BalanceService) {

  }

  ngOnInit() {
    this.balanceService.getBalance();
        .subscribe(data => this.balance = data);
  }

}
