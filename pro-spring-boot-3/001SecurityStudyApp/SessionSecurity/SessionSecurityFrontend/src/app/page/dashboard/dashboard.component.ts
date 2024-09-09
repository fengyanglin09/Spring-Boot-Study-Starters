import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {AsyncPipe} from "@angular/common";
import {take} from "rxjs";
import {Router} from "@angular/router";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";

@UntilDestroy()  // Apply the decorator to the component
@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    AsyncPipe
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})

export class DashboardComponent implements OnInit{

  sessionId!: String;

  constructor(protected authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
    this.getSessionInfo();

    /**
     * periodically check or refresh session
     * */
    setInterval(() => {
    this.authService.isAuthenticated().pipe(untilDestroyed(this), take(1)).subscribe(
      {
        next: (auth)=> {
          console.log('Session refreshed successfully');
        }
        ,
        error: (err) => {
          console.error('Session refresh failed', err);
          if (err.status === 401) {  // If unauthorized (session expired), redirect to login
            this.router.navigate(['/login']);
          }
        }
      }

    );
  }, 5 * 60 * 1000); // Check session status every 5 minutes

  }

  getSessionInfo(){
    this.authService.getSessionInformation()
      .pipe(take(1))
      .subscribe({
        next:(info) => {
          this.sessionId = info
        },
        error: () => {
          console.log("error getting session information")
        }
      })
  }


  invalidateSession(){
    this.authService.invalidateSession()
      .pipe(take(1))
      .subscribe({
        next:(info) => {
          this.router.navigate(['login'])
        },
        error: () => {
          console.log("error invalidating session")
        }
      })
  }


}
