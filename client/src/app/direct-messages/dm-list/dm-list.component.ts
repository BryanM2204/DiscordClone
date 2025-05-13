import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ConfigService} from '../../services/config.service';
import {NgForOf} from '@angular/common';

interface Friend {
  userId: number;
  username: string;
}

@Component({
  selector: 'app-dm-list',
  imports: [
    NgForOf
  ],
  standalone: true,
  templateUrl: './dm-list.component.html',
  styleUrl: './dm-list.component.css'
})

export class DmListComponent implements OnInit {
  constructor(private http: HttpClient, private configService: ConfigService) {}

  friends: Friend[] = [];

  @Output() friendSelected = new EventEmitter<any>();

  selectFriend(friend: any) {
    this.friendSelected.emit(friend);
  }

  ngOnInit(): void {
    this.fetchFriendsList();
  }

  fetchFriendsList(): void {
    this.http.get<{ name: string, payload: { friendsList: Friend[] } }>('http://localhost:8080/api/friends/list', { withCredentials: true })
      .subscribe({
        next: (response) => {
          this.friends = response.payload.friendsList;
        },
        error: (err) => {
          console.error('Error fetching friends:', err);
        }
      })
  }
}
