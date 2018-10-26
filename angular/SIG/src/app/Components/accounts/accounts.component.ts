<<<<<<< HEAD
import { BalanceService } from './../../Services/balance/balance.service';
=======
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from './../../Services/user/user.service';
import { Account } from './../../_models/account';
import { BalanceService } from './../../Services/balance.service';
>>>>>>> af5ff2e8416f429f92fca7acee819e3abb05c7d4
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
<<<<<<< HEAD
  balance: Balance;
  constructor(private balanceService: BalanceService) {
=======
  balance:Balance;
  accounts:Account[];
  constructor(private balanceService: BalanceService,
    private userService: UserService,
    private router: Router) {
>>>>>>> af5ff2e8416f429f92fca7acee819e3abb05c7d4

   }

  ngOnInit() {
    this.balanceService.getBalance()
     .subscribe(
      data => {
        this.balance = data;
      }

<<<<<<< HEAD
    );
=======
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
>>>>>>> af5ff2e8416f429f92fca7acee819e3abb05c7d4
  }

}
