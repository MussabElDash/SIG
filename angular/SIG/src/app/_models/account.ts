import { Security } from 'src/app/_models/security';
import { User } from "./user";

export class Account {
    id: number;
    accountType: string;
    accountName: string;
    balance: number;
    owner: User;
    securities: Security[];
}