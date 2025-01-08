import { Component } from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {NgIf} from "@angular/common";
import {ConfigService} from '../services/config.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
    imports: [
        FormsModule,
        NgIf,
        ReactiveFormsModule
    ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  profileForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });

  constructor(private configService: ConfigService, private router: Router) {}

  errorMessage: string = '';

  handleSubmit() {
    if(this.profileForm.valid) {
      const formData = this.profileForm.value;

      this.configService.postData('auth/register', formData).subscribe({
        next: (response: any) => {
          if(response.status == 201){
            console.log("Registration Success", response);
            this.router.navigate(['/home'])
          } else {
            console.log("Registration failed ", response);
            this.errorMessage = "Invalid credentials. Please try again.";
          }
        },
        error: (error) => {
          console.log("Registration Error", error);
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
