import { Component, OnInit, ElementRef, ViewChild } from "@angular/core";
import { HttpErrorResponse } from '@angular/common/http';

import { ServiceResponse } from "src/model/serviceResponse";

import { ServiceService } from "src/service/service.service";

@Component({
    selector: 'provided-services-tag',
    templateUrl: './provided-services.component.html',
    styleUrls: ['./styles.css']
})

export class ProvidedServicesComponent implements OnInit {
    public providedServices: ServiceResponse[] = [];

    constructor(private serviceService: ServiceService) { }

    ngOnInit(): void {
        this.serviceService.getProvidedServices().subscribe({
            next: (response: ServiceResponse[]) => {
                console.log("[LOG-INFO] Procurando todos os serviços vinculados ao prestador.");
                this.providedServices = response;
                console.log(this.providedServices);
            },
            error: (error: HttpErrorResponse) => {
                alert(error.error.message);
                console.log("[LOG-ERROR] Erro ao procurar os serviços vinculados ao prestador.");
                console.log(error);
            }
        });
    }
}