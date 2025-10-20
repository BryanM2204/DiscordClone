import {Component, OnInit, Input, ViewChild, ElementRef, AfterViewInit, AfterViewChecked} from '@angular/core';
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
export class ChatComponent implements OnInit, AfterViewInit {
  @Input() friend: any;
  newMessage = '';
  messages: any[] = [];


  private shouldScroll = false;
  @ViewChild('messagesContainer') private messagesContainer!: ElementRef;
  constructor(private websocketService: WebsocketComponent) {}


  ngOnInit(): void {
    this.websocketService.connect((msg) => {
      this.messages.push(msg);
      this.scrollToBottom();
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

  ngAfterViewInit(): void {
    this.scrollToBottom(); // initial scroll
  }

  private scrollToBottom(): void {
    const el = this.messagesContainer.nativeElement;
    el.scrollTop = el.scrollHeight;
  }



}
