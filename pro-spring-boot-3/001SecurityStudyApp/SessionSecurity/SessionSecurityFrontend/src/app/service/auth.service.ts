import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiUrl: string = environment.apiUri;

  constructor(private http: HttpClient) {
  }

  login(username: string, password: string) {
    const body = new URLSearchParams();
    body.set('username', username);
    body.set('password', password);

    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded'
    });
    return this.http.post(`${this.apiUrl}/api/auth/login`, body.toString(), { headers, withCredentials: true, responseType: 'text' });

  }

  getUserProfile() {
    return this.http.get(`${this.apiUrl}/user/profile`, { withCredentials: true });
  }


  /**
   * The backend should invalidate the session, and the session cookie will no longer be sent with requests.
   * */
  logout() {
    return this.http.post('http://api.example.com/logout', {}, { withCredentials: true });
  }

}
