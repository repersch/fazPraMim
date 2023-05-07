import { ActivatedRoute, ParamMap } from "@angular/router";
import { Component, OnInit } from "@angular/core";

import { ProfileResponse } from "src/model/profileResponse";

import { StorageService } from "src/service/storage.service";

@Component({
    selector: 'profile-tag',
    templateUrl: './profile.component.html',
    styleUrls: ['./styles.css']
})

export class ProfileComponent implements OnInit {
    private id: number | undefined;
    public profileData: ProfileResponse | undefined;

    constructor(private route: ActivatedRoute,
        private storageService: StorageService) { }

    ngOnInit(): void {
        this.route.paramMap.subscribe((params: ParamMap) => {
            this.id = +params.get('id')!;
        });
        this.profileData = this.storageService.getStorageProfileDetail();
    }
}