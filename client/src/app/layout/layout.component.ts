import { Component } from '@angular/core';
import {SideBarComponent} from "../side-bar/side-bar.component";
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-layout',
  standalone: true,
    imports: [
        SideBarComponent,
        RouterOutlet
    ],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css'
})
export class LayoutComponent {

}
