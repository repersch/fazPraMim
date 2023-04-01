import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs";

import { environment } from "src/environment/environment";
import { User } from "src/model/user";

@Injectable({ providedIn: 'root' })
export class UserService {
    private serverUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient) { }

    public addUser(user: User): Observable<User> {
        return this.http.post<User>(`${this.serverUrl}/registration`, user);
    }

    public getUsers(): Observable<User[]> {
        let localStorageItens = JSON.parse(localStorage.getItem('usuarioInfo')!);

        return this.http.get<User[]>(`${this.serverUrl}/users`, {
            headers: {
                'Authorization': `Bearer ${localStorageItens.token}`
            }
        });
    }
}