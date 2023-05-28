import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from "rxjs";

import { environment } from "src/environment/environment";

import { LoginRequest } from 'src/model/loginRequest';
import { TokenResponse } from 'src/model/tokenResponse';

@Injectable({ providedIn: 'root' })
export class AuthService {
    private serverUrl = environment.baseUrl;

    constructor(private http: HttpClient) { }

    public signin(loginRequest: LoginRequest): Observable<TokenResponse> {
        return this.http.post<TokenResponse>(`${this.serverUrl}/auth/signin`, loginRequest);
    }
}