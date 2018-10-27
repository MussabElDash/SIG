import { AssetPricing } from './assetpricing';
import { Account } from './account';

export class Order {
    id: number;
    ownerAccount: Account;
    ap: AssetPricing;
    amount: number;
}