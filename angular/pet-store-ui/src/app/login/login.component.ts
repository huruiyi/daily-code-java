import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { AuthService } from '../auth.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: [ './login.component.css' ]
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loginError = false;

  constructor(
    private fb: FormBuilder,
    private auth: AuthService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.createLoginForm();
  }

  createLoginForm() {
    this.loginForm = this.fb.group({
      email: [ '807776962@qq.com', Validators.required ],
      password: [ '807776962@qq.comX', Validators.required ]
    });
  }

  submitLoginForm() {
    this.auth.login(this.loginForm.get('email').value, this.loginForm.get('password').value)
    .pipe(first())
    .subscribe(
      pets => {
        this.router.navigateByData({
          url: [ '/pets' ],
          data: pets
        });
      },
      error => {
        this.loginError = true;
      }
    );
  }

  redirectToSignUp() {
    this.router.navigateByData({
      url: [ '/signup' ],
      data: null
    });
  }

}
