import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs";

import { environment } from "src/environment/environment";
import { User } from "src/model/user";

@Injectable({ providedIn: 'root' })
export class StorageService {

    public getStorageUserTokenInfo(): any {
        return JSON.parse(localStorage.getItem('userTokenInfo')!);
    }
}