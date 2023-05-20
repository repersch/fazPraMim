import { Component, OnInit, ElementRef, ViewChild } from "@angular/core";
import { HttpErrorResponse } from '@angular/common/http';

import { ServiceResponse } from "src/model/serviceResponse";
import { ServiceService } from "src/service/service.service";

@Component({
    selector: 'service-requests-tag',
    templateUrl: './service-requests.component.html',
    styleUrls: ['./styles.css']
})

export class ServiceRequestsComponent implements OnInit {
    public userServiceRequests: ServiceResponse[] = [];

    constructor(private serviceService: ServiceService) { }

    ngOnInit(): void {
        this.serviceService.getServiceRequests().subscribe({
            next: (response: ServiceResponse[]) => {
                console.log("[LOG-INFO] Procurando todos os serviços vinculados ao usuário.");
                this.userServiceRequests = response;
                console.log(this.userServiceRequests);
            },
            error: (error: HttpErrorResponse) => {
                alert(error.message);
                console.log("[LOG-ERROR] Erro ao procurar os serviços vinculados ao usuário.");
                console.log(error);
            }
        });
    }
}