import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from "rxjs";

import { environment } from "src/environment/environment";

import { Profile } from "src/model/profile";
import { ProfileResponse } from 'src/model/profileResponse';

import { StorageService } from './storage.service';

@Injectable({ providedIn: 'root' })
export class ProfileService {
    private serverUrl = environment.apiBaseUrl;

    public profileId: number | undefined;

    constructor(private http: HttpClient,
        private storageService: StorageService) { }

    public addProfile(profile: Profile): Observable<Profile> {
        let localStorageItens = this.storageService.getStorageUserTokenInfo();
        return this.http.post<Profile>(`${this.serverUrl}/profiles`, profile, {
            headers: {
                'Authorization': `Bearer ${localStorageItens.token}`
            }
        });
    }

    public getProfiles(): Observable<ProfileResponse[]> {
        return this.http.get<ProfileResponse[]>(`${this.serverUrl}/profiles`);
    }
}