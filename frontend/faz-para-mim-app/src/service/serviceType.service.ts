import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs";

import { environment } from "src/environment/environment";
import { ServiceType } from 'src/model/serviceType';
import { StorageService } from './storage.service';

@Injectable({ providedIn: 'root' })
export class ServiceTypeService {
    private serverUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient,
        private storageService: StorageService) { }

    public getServicesTypes(): Observable<ServiceType[]> {
        let localStorageItens = this.storageService.getStorageUserTokenInfo();

        return this.http.get<ServiceType[]>(`${this.serverUrl}/service-type`, {
            headers: {
                'Authorization': `Bearer ${localStorageItens.token}`
            }
        });
    }
}