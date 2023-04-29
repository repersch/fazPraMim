import { HttpErrorResponse } from '@angular/common/http';
import { Component } from "@angular/core";
import { NgForm } from '@angular/forms';

import { TokenResponse } from 'src/model/tokenResponse';
import { User } from 'src/model/user';
import { AuthService } from 'src/service/auth.service';
import { StorageService } from "src/service/storage.service";
import { UserService } from 'src/service/user.service';

@Component({
    selector: 'login-card-tag',
    templateUrl: './login-card.component.html',
    styleUrls: ['./styles.css']
})

export class LoginCardComponent {
    constructor(
        private accountCredentialsService: AuthService,
        private userService: UserService,
        private storageService: StorageService) { }

    public onAddUser(addUserForm: NgForm): void {
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
        this.accountCredentialsService.signin(loginForm.value).subscribe({
            next: (response: TokenResponse) => {
                console.log("[LOG-INFO] Usuário autenticado.");
                console.log(response);
                this.storageService.setStorageUserTokenInfo(response);
                this.setUserDataStorage();
                this.clearAndCloseLoginForm(loginForm);
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

    private setUserDataStorage(): void {
        this.userService.getUserById().subscribe({
            next: (response: User) => {
                this.storageService.setStorageUserDTO(response);
                const userData: User = {
                    id: response.id,
                    fullName: response.fullName,
                    username: response.username,
                    password: '',
                    phone: response.phone,
                    photo: response.photo,
                    birthDate: response.birthDate,
                    profileType: response.profileType
                }
                this.userService.userDTO = userData;


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
        let localStorageItens = this.storageService.getStorageUserData();
        let userPhoto = localStorageItens.photo;

        if (userPhoto === '' || userPhoto === null) {
            document.getElementById('profile-image')?.setAttribute('src', '../../../assets/img/person-icon.svg');
            document.getElementById('profile-image')?.setAttribute('class', 'btn rounded-circle btn-light');
        } else {
            document.getElementById('profile-image')?.setAttribute('src', userPhoto);
        }
    }
}