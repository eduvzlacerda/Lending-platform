import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import {Article} from "../model/article";
import {LendingRequest} from "../model/lendingRequest";

const API_URL = 'http://localhost:8080/lab/lendingProcesses/';


@Injectable({
  providedIn: 'root'
})
export class RequestService {
  constructor(private http: HttpClient) { }

  findIncomingPendingRequests(): Observable<LendingRequest[]> {
    // @ts-ignore
    let user = JSON.parse(localStorage.getItem('user'));
    return this.http.get<LendingRequest[]>(API_URL + "openRequestsLender/" + user.id);
  }

  findIncomingProcessedRequests(): Observable<LendingRequest[]> {
    // @ts-ignore
    let user = JSON.parse(localStorage.getItem('user'));
    return this.http.get<LendingRequest[]>(API_URL + "processedRequestsLender/" + user.id);
  }

  findMyRequests(): Observable<LendingRequest[]> {
    // @ts-ignore
    let user = JSON.parse(localStorage.getItem('user'));
    return this.http.get<LendingRequest[]>(API_URL + "requestsBorrower/" + user.id);
  }

  // @ts-ignore
  acceptRequest(requestId: string): Observable<LendingRequest>{
    let params = new HttpParams().set("id", requestId);
    this.http.post<LendingRequest>(API_URL + "acceptRequest/", {},{params}).subscribe(data =>
      {
        return data;
      });
  }

  // @ts-ignore
  declineRequest(requestId: string): Observable<LendingRequest> {
    let params = new HttpParams().set("id", requestId);
    this.http.post<LendingRequest>(API_URL + "rejectRequest/", {}, {params}).subscribe(data =>
    {
      return data;
    });
  }

  // @ts-ignore
  returnArticle(requestId: string): Observable<LendingRequest> {
    let params = new HttpParams().set("lendingProcessId", requestId);
    return this.http.post<LendingRequest>(API_URL + "giveBackArticle/", {}, {params});
  }
}
