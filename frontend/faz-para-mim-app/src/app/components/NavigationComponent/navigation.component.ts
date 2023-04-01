import { Component } from "@angular/core";

@Component({
    selector: 'navigation-tag',
    templateUrl: './navigation.component.html'
})

export class NavigationComponent {

    public getUserInfo(): string {
        return JSON.parse(localStorage.getItem('usuarioInfo')!);
    }

    public onUserLogout(): void {
        localStorage.removeItem('usuarioInfo');
    }
}