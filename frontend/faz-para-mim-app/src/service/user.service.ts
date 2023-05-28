import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from "rxjs";

import { environment } from "src/environment/environment";

import { User } from "src/model/user";

import { StorageService } from './storage.service';

@Injectable({ providedIn: 'root' })
export class UserService {
    private serverUrl = environment.apiBaseUrl;

    public userDTO: User | undefined;

    constructor(private http: HttpClient,
        private storageService: StorageService) { }

    public addUser(user: User): Observable<User> {
        return this.http.post<User>(`${this.serverUrl}/registration`, user);
    }

    public getUsers(): Observable<User[]> {
        let localStorageItens = this.storageService.getStorageUserTokenInfo();
        return this.http.get<User[]>(`${this.serverUrl}/users`, {
            headers: {
                'Authorization': `Bearer ${localStorageItens.token}`
            }
        });
    }

    public getUserById(): Observable<User> {
        let localStorageItens = this.storageService.getStorageUserTokenInfo();
        return this.http.get<User>(`${this.serverUrl}/users/${localStorageItens.id}`, {
            headers: {
                'Authorization': `Bearer ${localStorageItens.token}`
            }
        });
    }

    public updateUser(user: User): Observable<User> {
        let localStorageItens = this.storageService.getStorageUserTokenInfo();
        return this.http.put<User>(`${this.serverUrl}/users/${localStorageItens.id}`, user, {
            headers: {
                'Authorization': `Bearer ${localStorageItens.token}`
            }
        });
    }

    public updateProfileTypeToProvider(): Observable<User> {
        let localStorageItens = this.storageService.getStorageUserTokenInfo();
        return this.http.put<User>(`${this.serverUrl}/users/setPrestador/${localStorageItens.id}`, {}, {
            headers: {
                'Authorization': `Bearer ${localStorageItens.token}`
            }
        });
    }
}
