import { Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http'
import {NgForOf} from '@angular/common';

interface Server {
  id: number;
  name: string;
}

@Component({
  selector: 'app-side-bar',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './side-bar.component.html',
  styleUrl: './side-bar.component.css'
})

export class SideBarComponent implements OnInit {
  servers: Server[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchServers();
  }

  fetchServers(): void {
    this.http.get<{ name: string, payload: { serverList: Server[] } }>('http://localhost:8080/api/server/list/1')
      .subscribe({
        next: (response) => {
          this.servers = response.payload.serverList;
        },
        error: (err) => {
          console.error('Error fetching servers:', err);
        }
      });
  }
}
