import { Component, OnInit } from '@angular/core';
import {Article} from "../model/article";
import {ArticleService} from "../_services/article.service";
import {LendingRequest} from "../model/lendingRequest";
import {RequestService} from "../_services/request.service";
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-requests',
  templateUrl: './requests.component.html',
  styleUrls: ['./requests.component.css']
})
export class RequestsComponent implements OnInit {

  openLendingRequests: LendingRequest[] | undefined;
  processedLendingRequests: LendingRequest[] | undefined;
  openTabActive: boolean = true;

  constructor(private requestService: RequestService) { }

  ngOnInit(): void {
    this.requestService.findIncomingPendingRequests().subscribe(data => {
      this.openLendingRequests = data;
    });

    this.requestService.findIncomingProcessedRequests().subscribe(data => {
      this.processedLendingRequests = data;
    });

  }

  acceptRequest(requestId: string) {
    this.requestService.acceptRequest(requestId);
    // @ts-ignore
    this.openLendingRequests = this.openLendingRequests.filter(request => request.id != requestId);
  }

  declineRequest(requestId: string) {
    this.requestService.declineRequest(requestId);
    // @ts-ignore
    this.openLendingRequests = this.openLendingRequests.filter(request => request.id != requestId);
  }

  showOpen() {
    this.requestService.findIncomingPendingRequests().subscribe(data => {
      this.openLendingRequests = data;
    });
    this.openTabActive = true;
  }

  showProcessed() {
    this.requestService.findIncomingProcessedRequests().subscribe(data => {
      this.processedLendingRequests = data;
    });
    this.openTabActive = false;
  }
}
