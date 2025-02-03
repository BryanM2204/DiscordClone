import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {
  private readonly baseUrl = "http://localhost:8080/api";

  constructor(public http: HttpClient) {}

  postData(endpoint: string, data: any) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post(`${this.baseUrl}/${endpoint}`, data, {
      headers,
      observe: 'events',
      reportProgress: true,
      withCredentials: true
    });
  }
}
