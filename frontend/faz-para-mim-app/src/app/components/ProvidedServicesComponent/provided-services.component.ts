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
    public selectedRequestId = -1;

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

    public updateServiceStatus(serviceId: number, serviceStatusToUpdate: string): void {
        this.selectedRequestId = serviceId;
        this.serviceService.updateServiceStatus(serviceId, serviceStatusToUpdate).subscribe({
            next: (response: ServiceResponse) => {
                console.log(`[LOG-INFO] Status do serviço atualizado para ${serviceStatusToUpdate} com sucesso.`);
                console.log(response);
            },
            error: (error: HttpErrorResponse) => {
                alert(error.message);
                console.log(`[LOG-ERROR] Erro ao atualizar o status ${serviceStatusToUpdate} do serviço.`);
                console.log(error);
            }
        });
    }

    public isCreated(serviceStatus: string): boolean {
        if (serviceStatus === 'CREATED') {
            return true;
        }
        return false;
    }

    public isFinished(serviceStatus: string): boolean {
        if (serviceStatus === 'FINISHED') {
            return true;
        }
        return false;
    }

    public isCanceled(serviceStatus: string): boolean {
        if (serviceStatus === 'CANCELED') {
            return true;
        }
        return false;
    }
}