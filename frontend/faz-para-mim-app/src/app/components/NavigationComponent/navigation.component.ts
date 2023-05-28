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

    public isCustomer(): boolean {
        if (this.getUserDTOProfileType() === "Cliente") {
            return true;
        }
        return false;
    }

    private getUserDTOProfileType(): string {
        return this.getStorageUserDTO().profileType;
    }

    private getStorageUserDTO(): any {
        const userDTO = this.storageService.getStorageUserData();
        if (userDTO) {
            return userDTO;
        }
        return '';
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