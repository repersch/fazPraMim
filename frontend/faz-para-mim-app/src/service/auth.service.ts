import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs";
import { AccountCredentials } from 'src/model/accountCredentials';
import { Token } from 'src/model/token';

import { environment } from "src/environment/environment";

@Injectable({ providedIn: 'root' })
export class AuthService {
    private apiServerUrl = environment.baseUrl;

    constructor(private http: HttpClient) { }

    public signin(credential: AccountCredentials): Observable<Token> {
        return this.http.post<Token>(`${this.apiServerUrl}/auth/signin`, credential);
    }
}