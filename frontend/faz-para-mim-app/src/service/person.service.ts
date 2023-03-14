
import { HttpClient } from '@angular/common/http';
import { environment } from "src/environment/environment";
import { Person } from "src/model/person";
import { Observable } from "rxjs";
import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class PersonService {
    private apiServerUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient) { }

    public getPeople(): Observable<Person[]> {
        return this.http.get<Person[]>(`${this.apiServerUrl}/person`);
    }
}