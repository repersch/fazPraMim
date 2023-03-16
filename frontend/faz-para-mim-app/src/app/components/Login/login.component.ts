import { Component } from "@angular/core";
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { AccountCredentials } from 'src/model/accountCredentials';
import { Token } from 'src/model/token';
import { Person } from 'src/model/person';
import { AuthService } from 'src/service/auth.service';
import { PersonService } from "src/service/person.service";

@Component({
    selector: 'loginTag',
    templateUrl: './login.component.html',
    styleUrls: ['./styles.css']
})

export class Login {
    constructor(
        private accountCredentialsService: AuthService,
        private personService: PersonService) { }

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

    public onOpenModal(person: Person | undefined, mode: string): void {
        const container = document.getElementById('login-container');
        const button = document.createElement('button');
        button.type = 'button';
        button.style.display = 'none';
        button.setAttribute('data-toggle', 'modal');

        if (mode === 'createPerson') {
            button.setAttribute('data-bs-toggle', 'modal');
            button.setAttribute('data-bs-target', '#addPersonModal');
        }
        container?.appendChild(button);
        button.click();
    }

    public onAddPerson(addPersonForm: NgForm): void {
        document.getElementById('add-person-form')?.click();
        this.personService.addPerson(addPersonForm.value).subscribe(
            (response: Person) => {
                console.log(response);
                addPersonForm.reset();
            },
            (error: HttpErrorResponse) => {
                alert(error.message);
                addPersonForm.reset();
            });
    }
}