import { Component, OnInit, Input } from '@angular/core';
import { WebsocketComponent } from '../websocket/websocket.component';
import {FormsModule} from '@angular/forms';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css',
  imports: [
    FormsModule,
    NgForOf
  ]
})
export class ChatComponent implements OnInit {
  @Input() friend: any;
  newMessage = '';
  messages: any[] = [];

  constructor(private websocketService: WebsocketComponent) {}

  ngOnInit(): void {
    this.websocketService.connect((msg) => {
      this.messages.push(msg);
    });
  }

  send() {
    const message = {
      content: this.newMessage,
      type: 'CHAT'
    };
    this.websocketService.sendMessage(message);
    this.newMessage = '';
  }
}
