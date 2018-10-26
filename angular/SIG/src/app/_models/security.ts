import { AssetPricing } from "./assetpricing";

export class Security {
    id: number;
    type: string;
    ap: AssetPricing;
    amount: number;
    price: number;
}