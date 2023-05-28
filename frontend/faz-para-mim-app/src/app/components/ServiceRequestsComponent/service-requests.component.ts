import { Component, OnInit, ElementRef, ViewChild } from "@angular/core";
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

import { ServiceResponse } from "src/model/serviceResponse";
import { ServiceService } from "src/service/service.service";

@Component({
    selector: 'service-requests-tag',
    templateUrl: './service-requests.component.html',
    styleUrls: ['./styles.css']
})

export class ServiceRequestsComponent implements OnInit {
    public userServiceRequests: ServiceResponse[] = [];
    public selectedRequestId = -1;

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

    public evaluateService(serviceId: number): void {
        this.selectedRequestId = serviceId;
        this.serviceService.updateServiceStatus(serviceId, "FINISH").subscribe({
            next: (response: ServiceResponse) => {
                console.log("[LOG-INFO] Status do serviço atualizado para FINALIZADO com sucesso.");
                console.log(response);
            },
            error: (error: HttpErrorResponse) => {
                alert(error.message);
                console.log("[LOG-ERROR] Erro ao atualizar o status do serviço.");
                console.log(error);
            }
        });
    }

    public onEvaluateService(evaluateServiceForm: NgForm) {
        this.serviceService.evaluateService(this.selectedRequestId, evaluateServiceForm.value).subscribe({
            next: (response: ServiceResponse) => {
                console.log("[LOG-INFO] Serviço avaliado com sucesso.");
                console.log(response);
            },
            error: (error: HttpErrorResponse) => {
                alert(error.message);
                console.log("[LOG-ERROR] Erro ao avaliar o serviço.");
                console.log(error);
            }
        });
    }
}