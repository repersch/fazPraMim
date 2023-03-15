import { Component, OnInit } from '@angular/core';
import { Person } from 'src/model/person';
import { PersonService } from 'src/service/person.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public people: Person[] = [];

  constructor(private personService: PersonService) { }

  ngOnInit() {
    /* this.getPeople(); */
  }

  public getPeople(): void {
    this.personService.getPeople().subscribe(
      (response: Person[]) => {
        this.people = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
