import { ActivatedRoute, ParamMap } from "@angular/router";
import { Component, OnInit, ElementRef, ViewChild } from "@angular/core";
import { HttpErrorResponse } from '@angular/common/http';

import { User } from 'src/model/user';

import { UserService } from 'src/service/user.service';

@Component({
    selector: 'user-tag',
    templateUrl: './user.component.html',
    styleUrls: ['./styles.css']
})

export class UserComponent implements OnInit {
    @ViewChild('profileDetail') onProfileDetailOpen: ElementRef | undefined;
    private id: number | undefined;
    public userData: User | undefined;

    constructor(private route: ActivatedRoute,
        private userService: UserService) { }

    ngOnInit(): void {
        this.route.paramMap.subscribe((params: ParamMap) => {
            this.id = +params.get('id')!;
        })
        this.getUserData();
    }

    private getUserData(): void {
        let localStorageItens = JSON.parse(localStorage.getItem('userDTO')!);
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
        this.userData = userData;
    }

    public onOfferServiceButton() {
        this.userService.updateProfileTypeToProvider().subscribe({
            next: (response: User) => {
                console.log("[LOG-INFO] Usuário atualizado para prestador com sucesso.");
                console.log(response);
            },
            error: (error: HttpErrorResponse) => {
                alert(error.message);
                console.log("[LOG-ERROR] Erro ao atualizar Usuario para Prestador.");
                console.log(error);
            }
        });
    }
}