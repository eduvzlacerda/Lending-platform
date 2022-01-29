import { Component, OnInit } from '@angular/core';
import {LendingRequest} from "../model/lendingRequest";
import {RequestService} from "../_services/request.service";

@Component({
  selector: 'app-requests',
  templateUrl: './myRequests.component.html',
  styleUrls: ['./myRequests.component.css']
})
export class MyRequestsComponent implements OnInit {

  lendingRequests: LendingRequest[] | undefined;

  constructor(private requestService: RequestService) { }

  ngOnInit(): void {
    this.requestService.findMyRequests().subscribe(data => {
      this.lendingRequests = data;
    });
  }

}
