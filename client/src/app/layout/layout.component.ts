import { Component } from '@angular/core';
import {SideBarComponent} from "../side-bar/side-bar.component";
import { DmListComponent } from '../direct-messages/dm-list/dm-list.component';
import {RouterOutlet} from '@angular/router';
import {NgIf} from '@angular/common';
import {ChatComponent} from '../chat/chat.component';

@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [
    SideBarComponent,
    DmListComponent,
    RouterOutlet,
    NgIf,
    ChatComponent
  ],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css'
})
export class LayoutComponent {
  selectedFriend: any = null;

  onFriendSelected(friend: any) {
    this.selectedFriend = friend;
  }
}
