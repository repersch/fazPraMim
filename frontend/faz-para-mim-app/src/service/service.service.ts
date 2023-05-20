import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from "rxjs";

import { environment } from "src/environment/environment";
import { ServiceRequest } from 'src/model/serviceRequest';
import { ServiceResponse } from 'src/model/serviceResponse';
import { StorageService } from './storage.service';

@Injectable({ providedIn: 'root' })
export class ServiceService {
    private serverUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient,
        private storageService: StorageService) { }

    public addService(serviceRequest: ServiceRequest): Observable<ServiceResponse> {
        let localStorageItens = this.storageService.getStorageUserTokenInfo();
        return this.http.post<ServiceResponse>(`${this.serverUrl}/services`, serviceRequest, {
            headers: {
                'Authorization': `Bearer ${localStorageItens.token}`
            }
        });
    }
}