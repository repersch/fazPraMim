import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs";
import { Person } from "src/model/person";

import { environment } from "src/environment/environment";

@Injectable({ providedIn: 'root' })
export class PersonService {
    private apiServerUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient) { }

    public getPeople(): Observable<Person[]> {
        return this.http.get<Person[]>(`${this.apiServerUrl}/person`);
    }
}