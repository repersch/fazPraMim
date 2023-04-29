import { Injectable } from '@angular/core';

import { TokenResponse } from 'src/model/tokenResponse';
import { User } from "src/model/user";

@Injectable({ providedIn: 'root' })
export class StorageService {

    public getStorageUserTokenInfo(): any {
        return JSON.parse(localStorage.getItem('userTokenInfo')!);
    }

    public setStorageUserTokenInfo(tokenResponse: TokenResponse): void {
        localStorage.setItem('userTokenInfo', JSON.stringify({
            'id': tokenResponse.id,
            'token': tokenResponse.accessToken
        }));
    }

    public getStorageUserData(): any {
        return JSON.parse(localStorage.getItem('userDTO')!);
    }

    public setStorageUserDTO(userDTO: User): void {
        localStorage.setItem('userDTO', JSON.stringify({
            'id': userDTO.id,
            'fullName': userDTO.fullName,
            'username': userDTO.username,
            'phone': userDTO.phone,
            'photo': userDTO.photo,
            'birthDate': userDTO.birthDate,
            /* 'profileType': userDTO.profileType */
            'profileType': userDTO.profileType.charAt(0).toUpperCase() + userDTO.profileType.slice(1).toLowerCase()
        }));
    }
}