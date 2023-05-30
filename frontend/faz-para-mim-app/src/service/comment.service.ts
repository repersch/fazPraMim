import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from "rxjs";

import { environment } from "src/environment/environment";

import { CommentResponse } from 'src/model/commentResponse';

import { StorageService } from './storage.service';

@Injectable({ providedIn: 'root' })
export class CommentService {
    private serverUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient,
        private storageService: StorageService) { }

    public addComment(serviceId: number, commentRequest: any): Observable<CommentResponse> {
        let localStorageItens = this.storageService.getStorageUserTokenInfo();
        return this.http.post<CommentResponse>(`${this.serverUrl}/comments/${serviceId}`, commentRequest, {
            headers: {
                'Authorization': `Bearer ${localStorageItens.token}`
            }
        });
    }
}