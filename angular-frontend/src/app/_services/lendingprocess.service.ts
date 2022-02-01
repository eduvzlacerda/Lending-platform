import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import {Article} from "../model/article";
import {User} from "../model/user";
import {Lendingprocess} from "../model/lendingprocess";
//import {Article} from "../model/article";

const API_URL = 'http://localhost:8080/lab/lendingProcesses/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class LendingprocessService {
  constructor(private http: HttpClient) { }

  findAll() {
    return this.http.get<Lendingprocess>(API_URL);
  }

  addLendingProcess(article : Article, user: User): Observable<Lendingprocess> {

    // @ts-ignore
    // @ts-ignore
    let params = new HttpParams()
      .set("articleId", article.id)
      .set("userID", user.id);

    // let params = {
    //
    //   "articleId": article.id,
    //   "userID": user.id
    //
    // }

    console.log(params)

    return this.http.post<Lendingprocess>(API_URL + "addLendingProcessV2", {}, {params});

  }

}
