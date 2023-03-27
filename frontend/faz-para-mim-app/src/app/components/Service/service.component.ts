import { Component } from "@angular/core";
import { Router } from '@angular/router';

@Component({
    selector: 'serviceTag',
    templateUrl: './service.component.html',
    styleUrls: ['./styles.css']
})

export class ServiceComponentPage {
    constructor(private router: Router) { }

    public btnClick() {
        this.router.navigateByUrl('/user');
    }
}