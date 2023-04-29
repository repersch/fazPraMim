import { Component } from "@angular/core";

import { StorageService } from "src/service/storage.service";

@Component({
    selector: 'navigation-tag',
    templateUrl: './navigation.component.html',
    styleUrls: ['./styles.css']
})

export class NavigationComponent {
    constructor(private storageService: StorageService) { }

    public getStorageUserTokenInfo(): any {
        return this.storageService.getStorageUserTokenInfo();
    }

    public onLoginButton(): void {
        let container = document.getElementById('login-container');

        if (container?.style.display == 'block') {
            container!.style.display = 'none';
        } else {
            container!.style.display = 'block';
        }
    }

    public onUserLogout(): void {
        localStorage.removeItem('userTokenInfo');
        localStorage.removeItem('userProfile');
    }
}