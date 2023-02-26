import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { environment } from "src/environment/environment";

@Injectable({ providedIn: 'root' })
export class PersonService {
    private apiServerUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient) { }
}