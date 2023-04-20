import { Component, OnInit, ElementRef, ViewChild } from "@angular/core";
import { ActivatedRoute, ParamMap } from "@angular/router";
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

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
    public userProfile: User | undefined;

    constructor(private route: ActivatedRoute,
        private userService: UserService) { }

    ngOnInit(): void {
        this.route.paramMap.subscribe((params: ParamMap) => {
            this.id = +params.get('id')!;
        })
        this.getUserProfile();
    }

    ngAfterViewInit(): void {
        const modalElement = this.onProfileDetailOpen!.nativeElement;

        // Adiciona o manipulador de evento ao evento "shown.bs.modal"
        //this.getUserProfile();
        //modalElement.addEventListener('shown.bs.modal', this.onModalShown.bind(this));
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
        this.userProfile = userData;
    }

    public onOfferServiceButton() {
        this.userService.updateProfileTypeToProvider().subscribe(
            (response: User) => {
                console.log(response);
            },
            (error: HttpErrorResponse) => {
                alert(error.message);
            });
    }
}