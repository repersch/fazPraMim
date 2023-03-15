import { Component } from "@angular/core";
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { AccountCredentials } from 'src/model/accountCredentials';
import { Token } from 'src/model/token';
import { AuthService } from 'src/service/auth.service';

@Component({
    selector: 'loginTag',
    templateUrl: './login.component.html',
    styleUrls: ['./styles.css']
})

export class Login {
    constructor(private accountCredentialsService: AuthService) { }

    public onUserLogin(loginForm: NgForm): void {
        let accountCredential: AccountCredentials;
        accountCredential = loginForm.value;

        document.getElementById('login-user-form')?.click();
        console.log(accountCredential);
        this.accountCredentialsService.signin(accountCredential).subscribe(
            (response: Token) => {
                console.log(response);
                loginForm.reset();
            },
            (error: HttpErrorResponse) => {
                alert(error.message);
                loginForm.reset();
            });
    }
}