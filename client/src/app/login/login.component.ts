import {Component} from '@angular/core';
import {ReactiveFormsModule, FormControl, FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import {ConfigService} from '../services/config.service';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, NgIf],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  profileForm = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });

  constructor(private configService: ConfigService, private router: Router) {}

  errorMessage: string = '';

  handleSubmit() {
    if(this.profileForm.valid) {
      const formData = this.profileForm.value;

      this.configService.postData('auth/login', formData).subscribe({
        next: (response: any) => {
          if(response.status == 200){
            console.log("Login Success", response);
            this.router.navigate(['/home'])
          } else {
            console.log("Login failed ", response);
            this.errorMessage = "Invalid credentials. Please try again.";
          }
        },
        error: (error) => {
          console.log("Login Error", error);
          if(error.status == 400){
            this.errorMessage = "Invalid credentials. Please try again.";
          } else {
            this.errorMessage = "Unexpected error occurred. Please try again.";
          }
        }

      });
    } else {
      console.log("Form invalid");
      this.errorMessage = 'Please fill out all required fields';
    }
  };


}
