import { Component, ElementRef, ViewChild, OnInit } from "@angular/core";
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

import { LoginRequest } from 'src/model/loginRequest';
import { TokenResponse } from 'src/model/tokenResponse';
import { Profile } from 'src/model/profile';
import { User } from 'src/model/user';
import { ServiceType } from "src/model/serviceType";
import { AuthService } from 'src/service/auth.service';
import { ProfileService } from 'src/service/profile.service';
import { UserService } from 'src/service/user.service';
import { StorageService } from "src/service/storage.service";
import { ServiceTypeService } from "src/service/serviceType.service";

@Component({
    selector: 'user-modal-tag',
    templateUrl: 'user-modal.component.html',
    styleUrls: ['./styles.css']
})

export class UserModalComponent implements OnInit {
    @ViewChild('onUpdateUserModal') onUpdateUserModal: ElementRef | undefined;
    public editUser: User | undefined;
    public profileId = -1;
    public servicesTypes: ServiceType[] = [];

    ngOnInit(): void {
        this.getServicesTypes();
    }

    constructor(private userService: UserService,
        private profileService: ProfileService,
        private storageService: StorageService,
        private serviceTypeService: ServiceTypeService) {
    }

    private getServicesTypes(): void {
        this.serviceTypeService.getServicesTypes().subscribe({
            next: (response: ServiceType[]) => {
                this.servicesTypes = response;
                //console.log(response);
            },
            error: (error: HttpErrorResponse) => {
                alert(error.message);
                //addUserForm.reset();
            }
        });
    }

    private getUserInfo(): any {
        return this.storageService.getStorageUserTokenInfo();
    }

    public getUserIdStorage(): number {
        //return this.userService.userDTO!.id;


        this.profileId = this.userService.userDTO!.id;
        return this.profileId!;
    }

    public onAddUser(addUserForm: NgForm): void {
        //document.getElementById('add-user-form')?.click();
        this.userService.addUser(addUserForm.value).subscribe({
            next: (response: User) => {
                console.log(response);
                addUserForm.reset();
            },
            error: (error: HttpErrorResponse) => {
                alert(error.message);
                addUserForm.reset();
            }
        });
    }

    public onAddUserProfile(addUserProfileForm: NgForm): void {
        this.profileService.addProfile(addUserProfileForm.value).subscribe(
            (response: Profile) => {
                console.log("[LOG-INFO] Profile do Usuário adicionado com sucesso.");
                console.log(response);
                addUserProfileForm.reset();
            },
            (error: HttpErrorResponse) => {
                alert(error.message);
                console.log("[LOG-ERROR] Erro ao adicionar Profile do Usuário.");
                console.log(error);
            });
    }

    ngAfterViewInit(): void {
        const modalElement = this.onUpdateUserModal!.nativeElement;

        // Adiciona o manipulador de evento ao evento "shown.bs.modal"
        modalElement.addEventListener('shown.bs.modal', this.onModalShown.bind(this));
    }

    private onModalShown(event: Event): void {
        // Função a ser executada quando o modal for aberto
        this.getUserProfile();
    }

    public onUpdateUser(updateUserForm: NgForm): void {
        this.getUserProfile();
        document.getElementById('update-user-form')?.click();

        console.log("UPDATE FORM");
        console.log(updateUserForm.value);

        this.userService.updateUser(updateUserForm.value).subscribe(
            (response: User) => {
                console.log(response);
                updateUserForm.reset();
                this.clearAndCloseLoginForm(updateUserForm);
                this.setUserProfile(response);
                this.getUserProfile();
            },
            (error: HttpErrorResponse) => {
                alert(error.message);
                updateUserForm.reset();
            });
    }

    private clearAndCloseLoginForm(updateUserForm: NgForm): void {
        updateUserForm.reset();
        /*         let modal = document.getElementById('updateUserModal');
                modal?.hidden; */

        const modalElement = this.onUpdateUserModal!.nativeElement;
        modalElement.addEventListener('hidden.bs.modal');
    }


    private getUserProfile(): void {
        let localStorageItens = JSON.parse(localStorage.getItem('userProfile')!);
        const userData: User = {
            id: localStorageItens.id,
            fullName: localStorageItens.fullName,
            username: localStorageItens.username,
            password: '',
            phone: localStorageItens.phone,
            photo: localStorageItens.photo,
            birthDate: localStorageItens.birthDate,
            profileType: localStorageItens.profileType
        }
        this.editUser = userData;
    }

    private setUserProfile(response: User): void {
        localStorage.removeItem('userProfile');
        localStorage.setItem('userProfile', JSON.stringify({
            'id': response.id,
            'fullName': response.fullName,
            'username': response.username,
            'phone': response.phone,
            'photo': response.photo,
            'birthDate': response.birthDate
        }));
        this.updateUserProfilePhoto();
    }

    private updateUserProfilePhoto(): void {
        let localStorageItens = JSON.parse(localStorage.getItem('userProfile')!);
        document.getElementById('profile-image')?.setAttribute('src', localStorageItens.photo);
    }
}