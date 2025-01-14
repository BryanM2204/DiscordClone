import { Component } from '@angular/core';
import {ConfigService} from '../services/config.service';
import {Router} from '@angular/router';
import {SideBarComponent} from '../side-bar/side-bar.component';
import {LayoutComponent} from '../layout/layout.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    SideBarComponent,
    LayoutComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  constructor(private configService: ConfigService, private router: Router) {}



}
