import { Routes } from '@angular/router';
import {LoginComponent} from "./page/login/login.component";
import {DashboardComponent} from "./page/dashboard/dashboard.component";
import {authGuard} from "./guard/auth.guard";

export const routes: Routes = [

  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent, canActivate: [authGuard] },
];
