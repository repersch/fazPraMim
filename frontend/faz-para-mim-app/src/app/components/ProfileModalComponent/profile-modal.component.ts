import { Component } from "@angular/core";
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

import { ServiceResponse } from "src/model/serviceResponse";
import { ServiceService } from "src/service/service.service";
import { UserService } from 'src/service/user.service';
import { ProfileService } from "src/service/profile.service";

@Component({
    selector: 'profile-modal-tag',
    templateUrl: 'profile-modal.component.html',
    styleUrls: ['./styles.css']
})

export class ProfileModalComponent {
    public userId = -1;
    public profileId = -1;

    constructor(private userService: UserService,
        private serviceService: ServiceService,
        private profileService: ProfileService) {
    }

    public onAddService(addServiceForm: NgForm): void {
        console.log("FORMS:");
        console.log(addServiceForm.value);

        this.serviceService.addService(addServiceForm.value).subscribe({
            next: (response: ServiceResponse) => {
                console.log("[LOG-INFO] Serviço cadastrado com sucesso.");
                console.log(response);
                addServiceForm.reset();
            },
            error: (error: HttpErrorResponse) => {
                console.log("[LOG-ERROR] Erro ao cadastrar novo serviço.");
                console.log(error);
                alert(error.message);
            }
        });
    }

    public getUserIdStorage(): number {
        if (this.userService.userDTO?.id) {
            this.userId = this.userService.userDTO.id;
            return this.userId;
        } else {
            return 0;
        }
    }

    public getProfileIdStorage(): number {
        if (this.profileService.profileId) {
            this.profileId = this.profileService.profileId;
            return this.profileId;
        }
        else {
            return 0;
        }
    }
}