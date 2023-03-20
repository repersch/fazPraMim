import { Component } from "@angular/core";

@Component({
    selector: 'navTag',
    templateUrl: './nav.component.html'
})

export class NavigationBar {

    public onLogin() {
        return JSON.parse(localStorage.getItem('usuarioInfo')!);
    }

    public onLogout() {
        localStorage.removeItem('usuarioInfo');
    }
}