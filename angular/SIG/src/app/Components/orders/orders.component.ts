import { AssetPricing } from './../../_models/assetpricing';
import { InvestmentService } from './../../Services/investment/investment.service';
import { Component, OnInit, NO_ERRORS_SCHEMA } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from './../../Services/user/user.service';
import { Account } from './../../_models/account';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';



@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
  orderForm: FormGroup;
  accountId:number;
  account:Account;
  assetPricings:AssetPricing[];
  searchOption:string ="ticker";
  searchVal:string = "qwerty";

  constructor(private userService:UserService,
    private investmentService:InvestmentService,
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder
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
        }, 
        error=>{ console.log("whoops")});

        this.investmentService
        .getTickerPrices()
        .subscribe(
          data =>{
            this.assetPricings = data;
            console.log(data);
          }
        )

        this.orderForm = this.formBuilder.group({
          quantity: ['', Validators.required]
        });
    }

    get f() { return this.orderForm.controls; }

    newOrder( tickerSymbol:string){
      this.investmentService.makeOrder(tickerSymbol, this.accountId, this.f.quantity.value)
      .subscribe(
        data =>{
          this.router.navigate(['account-details', this.accountId]);
        }
      )

      //Route to account details page
			this.router.navigate(['account-details', this.accountId]);
		

    }

    selectSearchHandler (event: any) {
      this.searchOption = event.target.value;
    }

    displaySearch (event: any) {
      this.searchVal = event.target.value.toUpperCase();
      console.log(this.searchVal);
    }


}
