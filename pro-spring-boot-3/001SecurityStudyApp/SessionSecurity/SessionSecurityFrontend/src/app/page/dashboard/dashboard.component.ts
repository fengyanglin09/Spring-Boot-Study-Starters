import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {AsyncPipe} from "@angular/common";
import {take} from "rxjs";
import {Router} from "@angular/router";

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
