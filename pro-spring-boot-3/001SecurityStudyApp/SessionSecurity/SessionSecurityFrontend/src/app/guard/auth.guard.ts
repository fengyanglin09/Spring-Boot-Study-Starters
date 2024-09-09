import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {AuthService} from "../service/auth.service";
import {map} from "rxjs";

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);  // Inject the AuthService to check session status
  const router = inject(Router);  // Inject the Router for navigation

  return authService.isAuthenticated().pipe(
    map(isAuthenticated => {
      if (!isAuthenticated) {
        router.navigate(['login']);  // Redirect to login if not authenticated
        return false;
      }
      return true;  // Allow access if authenticated
    })
  );
};
