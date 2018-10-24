import { AccountsComponent } from './Components/accounts/accounts.component';
import { AppComponent } from './app.component';

import { Routes } from '@angular/router';

export const appRoutes: Routes = [
    {
        path: 'home',
        component: AppComponent
    },
    {
        path: 'accounts-summary',
        component: AccountsComponent
    },
];

