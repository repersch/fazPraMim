import { Component } from "@angular/core";
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { Observable } from "rxjs";

import { LoginRequest } from 'src/model/loginRequest';
import { TokenResponse } from 'src/model/tokenResponse';
import { User } from 'src/model/user';
import { AuthService } from 'src/service/auth.service';
import { UserService } from "src/service/user.service";
import { StorageService } from "src/service/storage.service";

@Component({
    selector: 'navigation-tag',
    templateUrl: './navigation.component.html',
    styleUrls: ['./styles.css']
})

export class NavigationComponent {
    constructor(private userService: UserService,
        private storageService: StorageService) { }

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

    public getUserInfo(): any {
        return this.storageService.getStorageUserTokenInfo();
    }
}





/*     public getUser(): Observable<User> {
    let userInfo = JSON.parse(localStorage.getItem('userTokenInfo')!);
    //this.userAuth = this.userService.getUserById(userInfo.id);
    return this.userService.getUserById();
} */

/* public onOpenModal(user: User | undefined, mode: string): void {
    const container = document.getElementById('login-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');

    if (mode === 'signinUser') {
        button.setAttribute('data-bs-toggle', 'modal');
        button.setAttribute('data-bs-target', '#signinUserModal');
    } else if (mode === 'createUser') {
        button.setAttribute('data-bs-toggle', 'modal');
        button.setAttribute('data-bs-target', '#addUserModal');
    }
    container?.appendChild(button);
    button.click();
} */



/* public onUserLogin(loginForm: NgForm): void {
    let accountCredential: AccountCredentials;
    accountCredential = loginForm.value;

    document.getElementById('login-user-form')?.click();
    this.accountCredentialsService.signin(accountCredential).subscribe(
        (response: Token) => {
            localStorage.setItem('userTokenInfo', JSON.stringify({
                'id': response.id,
                'username': response.username,
                'token': response.accessToken
            }));
            console.log("[LOG-INFO] Usuário autenticado");
            console.log(response);
            loginForm.reset();
        },
        (error: HttpErrorResponse) => {
            alert(error.message);
            loginForm.reset();
        });
}

public onAddUser(addUserForm: NgForm): void {
    document.getElementById('add-user-form')?.click();

    console.log("Teste");
    console.log(addUserForm.value);


    this.userService.addUser(addUserForm.value).subscribe(
        (response: User) => {
            console.log(response);
            addUserForm.reset();
        },
        (error: HttpErrorResponse) => {
            alert(error.message);
            addUserForm.reset();
        });
} */