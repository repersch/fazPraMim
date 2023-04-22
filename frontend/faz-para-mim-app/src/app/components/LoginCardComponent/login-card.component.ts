import { Component } from "@angular/core";
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

import { LoginRequest } from 'src/model/loginRequest';
import { TokenResponse } from 'src/model/tokenResponse';
import { User } from 'src/model/user';
import { AuthService } from 'src/service/auth.service';
import { UserService } from 'src/service/user.service';

@Component({
    selector: 'login-card-tag',
    templateUrl: './login-card.component.html',
    styleUrls: ['./styles.css']
})

export class LoginCardComponent {
    constructor(
        private accountCredentialsService: AuthService,
        private userService: UserService) { }

    public onAddUser(addUserForm: NgForm): void {
        //document.getElementById('add-user-form')?.click();
        this.userService.addUser(addUserForm.value).subscribe({
            next: (response: User) => {
                console.log("[LOG-INFO] Usuário cadastrado com sucesso.");
                console.log(response);
                addUserForm.reset();
            },
            error: (error: HttpErrorResponse) => {
                console.log("[LOG-ERROR] Erro ao cadastrar novo usuário.");
                console.log(error);
                alert(error.message);
            }
        });
    }

    public onUserLogin(loginForm: NgForm): void {
        //let loginRequest = loginForm.value;
        //document.getElementById('login-user-form')?.click();
        this.accountCredentialsService.signin(loginForm.value).subscribe({
            next: (response: TokenResponse) => {
                localStorage.setItem('userTokenInfo', JSON.stringify({
                    'id': response.id,
                    'token': response.accessToken
                }));
                console.log("[LOG-INFO] Usuário autenticado.");
                console.log(response);
                this.clearAndCloseLoginForm(loginForm);
                this.setUserProfileStorage();
            },
            error: (error: HttpErrorResponse) => {
                console.log("[LOG-ERROR] Erro ao autenticar usuário.");
                console.log(error);
                alert(error.message);
            }
        });
    }

    private clearAndCloseLoginForm(loginForm: NgForm): void {
        loginForm.reset();
        let container = document.getElementById('login-container');
        container!.style.display = 'none';
    }

    private setUserProfileStorage(): void {
        this.userService.getUserById().subscribe({
            next: (response: User) => {
                localStorage.setItem('userProfile', JSON.stringify({
                    'id': response.id,
                    'fullName': response.fullName,
                    'username': response.username,
                    'phone': response.phone,
                    'photo': response.photo,
                    'birthDate': response.birthDate,
                    'profileType': response.profileType
                    //'profileType': response.profileType.charAt(0).toUpperCase() + response.profileType.slice(1).toLowerCase()
                }));
                this.setUserProfilePhoto();
            },
            error: (error: HttpErrorResponse) => {
                alert(error.message);
                console.log("[LOG-ERROR] Erro ao atualizar Cliente para Prestador.");
                console.log(error);
            }
        });
    }

    private setUserProfilePhoto(): void {
        let localStorageItens = JSON.parse(localStorage.getItem('userProfile')!);
        let userPhoto = localStorageItens.photo;

        if (userPhoto === '' || userPhoto === null) {
            document.getElementById('profile-image')?.setAttribute('src', '../../../assets/img/person-icon.svg');
            document.getElementById('profile-image')?.setAttribute('class', 'rounded-circle bg-light');
        } else {
            document.getElementById('profile-image')?.setAttribute('src', userPhoto);
        }
    }
}

/*     public onOpenModal(user: User | undefined, mode: string): void {
    const container = document.getElementById('loginContainer');
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

/*     public onAddUser(addUserForm: NgForm): void {
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
