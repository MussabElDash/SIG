import { AssetPricing } from './../../_models/assetpricing';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class InvestmentService {

  constructor(private http: HttpClient) { }

  getTickerPrices(){
    
    return this.http.get<AssetPricing[]>("http://localhost:8085/SIG/GetAssetPricingServlet");
  }

  makeOrder(ticker: string, aid: number, quant: number){
    const httpOptions = {
			headers: new HttpHeaders({
				'Content-Type': 'application/x-www-form-urlencoded'
			}),
    };
    
    let body = new HttpParams();
		body = body.set('tickerSymbol', ticker);
		body = body.set('aid', aid.toString());
    body = body.set('amount', quant.toString());
    
    console.log(body);
    return this.http.post("http://localhost:8085/SIG/NewOrderServlet",
			body, httpOptions);
  }
}
