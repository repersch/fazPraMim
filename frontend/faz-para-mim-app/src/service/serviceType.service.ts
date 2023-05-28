import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from "rxjs";

import { environment } from "src/environment/environment";

import { ServiceType } from 'src/model/serviceType';

@Injectable({ providedIn: 'root' })
export class ServiceTypeService {
    private serverUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient) { }

    public getServicesTypes(): Observable<ServiceType[]> {
        return this.http.get<ServiceType[]>(`${this.serverUrl}/service-type`);
    }
}