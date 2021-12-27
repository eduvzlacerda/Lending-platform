import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {BehaviorSubject, map, Observable} from 'rxjs';
import {Router} from "@angular/router";
import {User} from "../model/user";

const AUTH_API_LOGIN = 'http://localhost:8080/lab/authentication/';
const AUTH_API_REGISTER = 'http://localhost:8080/lab/users/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private userSubject: BehaviorSubject<User>;
  private user: Observable<User>;

  constructor(private http: HttpClient, private router: Router) {
    // @ts-ignore
    this.userSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('user')));
    // @ts-ignore
    this.user = this.userSubject.asObservable();
  }

  public get userValue(): User {
    // @ts-ignore
    return this.userSubject.value;
  }

  login(email: string, password: string): Observable<any> {
    return this.http.post<User>(AUTH_API_LOGIN, {email, password}, httpOptions)
      .pipe(map(user => {
        localStorage.setItem('user', JSON.stringify(user));
        this.userSubject.next(user);
        return user;
      }));
  }

  register(name: string, email: string, password: string): Observable<any> {
    return this.http.post(AUTH_API_REGISTER, {
      name,
      email,
      password
    }, httpOptions);
  }

  logout() {
    // remove user from local storage and set current user to null
    localStorage.removeItem('user');
    // @ts-ignore
    this.userSubject.next(null);
    this.router.navigate(['/account/login']);
  }

}
