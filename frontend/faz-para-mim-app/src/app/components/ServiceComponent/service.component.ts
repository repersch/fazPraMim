import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

import { ProfileResponse } from "src/model/profileResponse";
import { ProfileService } from "src/service/profile.service";
import { StorageService } from "src/service/storage.service";

@Component({
    selector: 'service-tag',
    templateUrl: './service.component.html',
    styleUrls: ['./styles.css']
})

export class ServiceComponent implements OnInit {
    public profiles: ProfileResponse[] = [];
    public profileDetail: ProfileResponse | undefined;

    constructor(private router: Router,
        private profileService: ProfileService,
        private storageService: StorageService) { }

    ngOnInit() {
        localStorage.clear();
        this.getProfiles();
    }

    private getProfiles(): void {
        this.profileService.getProfiles().subscribe({
            next: (response: ProfileResponse[]) => {
                this.profiles = response;
            },
            error: (error: HttpErrorResponse) => {
                alert(error.message);
            }
        });
    }

    public btnClick(profile: ProfileResponse) {
        this.profileDetail = profile;
        this.storageService.setStorageProfileDetail(profile);
        this.router.navigateByUrl('/profile/{{this.profileDetail.id}}');
    }
}