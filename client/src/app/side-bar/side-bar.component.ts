import { Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http'
import {NgForOf, NgIf} from '@angular/common';
import {ConfigService} from '../services/config.service';
import {Router} from '@angular/router';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';

interface Server {
  id: number;
  name: string;
}

@Component({
  selector: 'app-side-bar',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,
    ReactiveFormsModule
  ],
  templateUrl: './side-bar.component.html',
  styleUrl: './side-bar.component.css'
})

export class SideBarComponent implements OnInit {
  constructor(private http: HttpClient, private configService: ConfigService) {}

  servers: Server[] = [];

  showPopup = false;
  isClosing = false;
  showCreateForm = false;

  createForm = new FormGroup({
    name: new FormControl('', Validators.required),
    description: new FormControl('', Validators.required),
  });

  errorMessage = "";

  ngOnInit(): void {
    this.fetchServers();
  }

  fetchServers(): void {
    this.http.get<{ name: string, payload: { serverList: Server[] } }>('http://localhost:8080/api/server/list', { withCredentials: true })
      .subscribe({
        next: (response) => {
          this.servers = response.payload.serverList;
        },
        error: (err) => {
          console.error('Error fetching servers:', err);
        }
      });
  }

  createServer(): void {
    if(this.createForm.valid) {
      const formData = this.createForm.value;

      this.configService.postData("server/create", formData).subscribe({
        next: (response: any) => {
          if(response.status == 201) {
            console.log("Creation of server Success", response);
          }
        },
        error: (error) => {
          console.log("Creation of Server Failed", error);
          if(error.status == 404 || error.status == 403) {
            this.errorMessage = "Invalid information inputted, or server already exists!";
          } else {
            this.errorMessage = "Unexpected error occurred. Please try again";
          }
        }
      });
    } else {
      console.log("Form invalid");
      this.errorMessage = 'Please fill out all required fields';
    }

  }


  hidePopup() {
    this.isClosing = true;
    setTimeout(() => {
      this.showPopup = false;
      this.isClosing = false;
    }, 300); // Match this with your animation duration
  }

  togglePopup() {
    if(this.showPopup) {
      this.hidePopup();
    } else {
      this.showPopup = true;
      this.showCreateForm = false;
    }
  }

  openCreateForm() {
    this.showCreateForm = true;
    this.showPopup = false;
    setTimeout(() => {
      this.showPopup = true;
    }, 0);
  }

  joinServer() {
    this.showCreateForm = false;
    console.log('Join server clicked');
    this.togglePopup();
  }

}
