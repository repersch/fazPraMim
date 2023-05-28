import { Component, ElementRef, ViewChild, OnInit } from "@angular/core";
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

import { Profile } from 'src/model/profile';
import { ServiceType } from "src/model/serviceType";
import { User } from 'src/model/user';

import { ProfileService } from 'src/service/profile.service';
import { ServiceTypeService } from "src/service/serviceType.service";
import { StorageService } from "src/service/storage.service";
import { UserService } from 'src/service/user.service';

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
            },
            error: (error: HttpErrorResponse) => {
                alert(error.message);
            }
        });
    }

    public getUserIdStorage(): number {
        if (this.userService.userDTO?.id) {
            this.profileId = this.userService.userDTO.id;
            return this.profileId;
        } else {
            return 0;
        }
    }

    public onAddUser(addUserForm: NgForm): void {
        this.userService.addUser(addUserForm.value).subscribe({
            next: (response: User) => {
                console.log("[LOG-INFO] Usuário adicionado com sucesso.");
                console.log(response);
                addUserForm.reset();
            },
            error: (error: HttpErrorResponse) => {
                alert(error.message);
                console.log("[LOG-ERROR] Erro ao adicionar Usuário.");
                console.log(error);
            }
        });
    }

    public onAddUserProfile(addUserProfileForm: NgForm): void {
        this.profileService.addProfile(addUserProfileForm.value).subscribe({
            next: (response: Profile) => {
                console.log("[LOG-INFO] Profile do Usuário adicionado com sucesso.");
                console.log(response);
                addUserProfileForm.reset();
            },
            error: (error: HttpErrorResponse) => {
                alert(error.message);
                console.log("[LOG-ERROR] Erro ao adicionar Profile do Usuário.");
                console.log(error);
            }
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
        this.userService.updateUser(updateUserForm.value).subscribe({
            next: (response: User) => {
                console.log("[LOG-INFO] Usuário alterado com sucesso.");
                console.log(response);
                updateUserForm.reset();
                this.clearAndCloseLoginForm(updateUserForm);
                this.setUserProfile(response);
                this.getUserProfile();
            },
            error: (error: HttpErrorResponse) => {
                alert(error.message);
                console.log("[LOG-ERROR] Erro ao alterar Usuário.");
                console.log(error);
            }
        });
    }

    private clearAndCloseLoginForm(updateUserForm: NgForm): void {
        updateUserForm.reset();
    }

    private getUserProfile(): void {
        let localStorageItens = this.storageService.getStorageUserData();
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
        localStorage.removeItem('userDTO');
        this.storageService.setStorageUserDTO(response);
        this.updateUserProfilePhoto();
    }

    private updateUserProfilePhoto(): void {
        let localStorageItens = JSON.parse(localStorage.getItem('userDTO')!);
        document.getElementById('profile-image')?.setAttribute('src', localStorageItens.photo);
    }
}