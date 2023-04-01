import { Component } from "@angular/core";
import { Router } from '@angular/router';

@Component({
    selector: 'service-tag',
    templateUrl: './service.component.html',
    styleUrls: ['./styles.css']
})

export class ServiceComponent {
    constructor(private router: Router) { }

    public btnClick() {
        this.router.navigateByUrl('/user');
    }
}