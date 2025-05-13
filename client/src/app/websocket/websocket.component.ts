import { Injectable } from '@angular/core';
import SockJS from 'sockjs-client';
import { Client, IMessage } from '@stomp/stompjs';

@Injectable({
  providedIn: 'root'
})
export class WebsocketComponent {
  private stompClient: Client;

  constructor() {
    this.stompClient = new Client({
      webSocketFactory: () => new SockJS('http://localhost:8080/websocket'),
      reconnectDelay: 5000,
    });
  }

  connect(onMessage: (msg: any) => void) {
    this.stompClient.onConnect = () => {
      this.stompClient.subscribe('/topic/public', (message: IMessage) => {
        const body = JSON.parse(message.body);
        onMessage(body);
      });
    };

    this.stompClient.activate();
  }

  sendMessage(message: any) {
    this.stompClient.publish({
      destination: '/app/chat.send',
      body: JSON.stringify(message)
    });
  }
}
