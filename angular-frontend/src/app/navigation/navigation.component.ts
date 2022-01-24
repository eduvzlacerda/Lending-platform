import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {User} from "../model/user";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {

  }

  public getRole(): string {
    // @ts-ignore
    let user = JSON.parse(localStorage.getItem('user'));
    if(user != null) {
      return user.roles[0].name;
    }
    else {
      return "";
    }
  }

  public loggedInUserName(): string {
    // @ts-ignore
    let user = JSON.parse(localStorage.getItem('user'));
    //console.log(user);
    return user.name;
  }

  logout(): void {
    console.log("logout");
    localStorage.removeItem('user');
    this.router.navigate(['/articles']);
  }

  loggedIn(): boolean {
    return localStorage.getItem('user') != null;
  }

  redirectToLogin() {
    this.router.navigate(['/login']);
  }

  redirectToRegister() {
    this.router.navigate(['/register']);
  }
}
