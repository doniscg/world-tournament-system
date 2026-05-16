import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { DistributionComponent } from './distribution/distribution.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [

    RouterOutlet,

    DistributionComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'mundial-frontend';
}
