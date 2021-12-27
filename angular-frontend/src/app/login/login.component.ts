import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: any = {
    email: null,
    password: null
  };
  errorMessage = '';
  isLoginFailed = false;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {

  }

  onSubmit(): void {
    const { email, password } = this.form;
    console.log(this.form);
    console.log(email);
    console.log(password);
    this.authService.login(email, password).subscribe({
      next: data => {
        this.router.navigate(['/articles']);
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    });
  }

}
