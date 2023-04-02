import { Component } from "@angular/core";
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

import { AccountCredentials } from 'src/model/accountCredentials';
import { Token } from 'src/model/token';
import { User } from 'src/model/user';
import { AuthService } from 'src/service/auth.service';
import { UserService } from 'src/service/user.service';

@Component({
    selector: 'user-modal-tag',
    templateUrl: 'user-modal.component.html',
    styleUrls: ['./styles.css']
})

export class UserModalComponent {
    constructor(
        private accountCredentialsService: AuthService,
        private userService: UserService) { }

    public onAddUser(addUserForm: NgForm): void {
        document.getElementById('add-user-form')?.click();

        console.log("Teste");
        console.log(addUserForm.value);


        this.userService.addUser(addUserForm.value).subscribe(
            (response: User) => {
                console.log(response);
                addUserForm.reset();
            },
            (error: HttpErrorResponse) => {
                alert(error.message);
                addUserForm.reset();
            });
    }
}