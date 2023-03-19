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
        let localStorageItens = JSON.parse(localStorage.getItem('usuarioInfo')!);

        return this.http.get<Person[]>(`${this.apiServerUrl}/person`, {
            headers: {
                'Authorization': `Bearer ${localStorageItens.token}`
            }
        });
    }

    public addPerson(person: Person): Observable<Person> {
        return this.http.post<Person>(`${this.apiServerUrl}/registration`, person);
    }
}