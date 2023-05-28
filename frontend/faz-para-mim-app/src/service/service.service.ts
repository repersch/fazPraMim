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

    public getServiceRequests(): Observable<ServiceResponse[]> {
        let localStorageItens = this.storageService.getStorageUserTokenInfo();
        return this.http.get<ServiceResponse[]>(`${this.serverUrl}/services/client/${localStorageItens.id}`, {
            headers: {
                'Authorization': `Bearer ${localStorageItens.token}`
            }
        });
    }

    public getProvidedServices(): Observable<ServiceResponse[]> {
        let localStorageItens = this.storageService.getStorageUserTokenInfo();
        return this.http.get<ServiceResponse[]>(`${this.serverUrl}/services/provider/${localStorageItens.id}`, {
            headers: {
                'Authorization': `Bearer ${localStorageItens.token}`
            }
        });
    }

    public updateServiceStatus(serviceId: number, serviceStatus: string): Observable<ServiceResponse> {
        let localStorageItens = this.storageService.getStorageUserTokenInfo();
        let serviceStatusCode = 0;

        if (serviceStatus === "FINISH") {
            serviceStatusCode = 3;
        }

        return this.http.put<ServiceResponse>(`${this.serverUrl}/services/${serviceId}/${serviceStatusCode}`, {}, {
            headers: {
                'Authorization': `Bearer ${localStorageItens.token}`
            }
        });
    }

    public evaluateService(serviceId: number, ratingRequest: any): Observable<ServiceResponse> {
        let localStorageItens = this.storageService.getStorageUserTokenInfo();

        return this.http.put<ServiceResponse>(`${this.serverUrl}/services/evaluate/${serviceId}`, ratingRequest.rating, {
            headers: {
                'Authorization': `Bearer ${localStorageItens.token}`
            }
        });
    }
}