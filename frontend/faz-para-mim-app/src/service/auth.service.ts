import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs";

import { environment } from "src/environment/environment";
import { AccountCredentials } from 'src/model/accountCredentials';
import { Token } from 'src/model/token';

@Injectable({ providedIn: 'root' })
export class AuthService {
    private serverUrl = environment.baseUrl;

    constructor(private http: HttpClient) { }

    public signin(accountCredential: AccountCredentials): Observable<Token> {
        return this.http.post<Token>(`${this.serverUrl}/auth/signin`, accountCredential);
    }
}