import { Component, OnInit, ElementRef, ViewChild } from "@angular/core";
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

import { CommentResponse } from "src/model/commentResponse";
import { ServiceResponse } from "src/model/serviceResponse";

import { CommentService } from "src/service/comment.service";
import { ServiceService } from "src/service/service.service";

@Component({
    selector: 'service-requests-tag',
    templateUrl: './service-requests.component.html',
    styleUrls: ['./styles.css']
})

export class ServiceRequestsComponent implements OnInit {
    public userServiceRequests: ServiceResponse[] = [];
    public selectedRequestId = -1;

    constructor(private commentService: CommentService,
        private serviceService: ServiceService) { }

    ngOnInit(): void {
        this.serviceService.getServiceRequests().subscribe({
            next: (response: ServiceResponse[]) => {
                console.log("[LOG-INFO] Procurando todos os serviços vinculados ao usuário.");
                this.userServiceRequests = response;
                console.log(this.userServiceRequests);
            },
            error: (error: HttpErrorResponse) => {
                alert(error.error.message);
                console.log("[LOG-ERROR] Erro ao procurar os serviços vinculados ao usuário.");
                console.log(error);
            }
        });
    }

    public setSelectedRequestId(serviceId: number): void {
        this.selectedRequestId = serviceId;
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

        if (evaluateServiceForm.value.comment) {
            this.commentService.addComment(this.selectedRequestId, evaluateServiceForm.value.comment).subscribe({
                next: (response: CommentResponse) => {
                    console.log("[LOG-INFO] Comentário do serviço selecionado enviado com sucesso.");
                    console.log(response);
                },
                error: (error: HttpErrorResponse) => {
                    alert(error.message);
                    console.log("[LOG-ERROR] Erro ao enviar comentário do serviço selecionado.");
                    console.log(error);
                }
            });
        }
    }

    public onCommentService(commentServiceForm: NgForm) {
        this.commentService.addComment(this.selectedRequestId, commentServiceForm.value.comment).subscribe({
            next: (response: CommentResponse) => {
                console.log("[LOG-INFO] Comentário do serviço selecionado enviado com sucesso.");
                console.log(response);
            },
            error: (error: HttpErrorResponse) => {
                alert(error.message);
                console.log("[LOG-ERROR] Erro ao enviar comentário do serviço selecionado.");
                console.log(error);
            }
        });
    }

    public isAccepted(serviceStatus: string): boolean {
        if (serviceStatus === 'ACCEPTED') {
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

    public isRated(rate: number): boolean {
        if (rate == 0) {
            return false;
        }
        return true;
    }
}