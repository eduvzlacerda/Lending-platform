import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Article} from "../model/article";

const API_URL = 'http://localhost:8080/lab/articles/';


@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  constructor(private http: HttpClient) { }

  // TODO: this get method referece to paging! add paging in UI as well!
  findAll(): Observable<Article[]> {
    return this.http.get<Article[]>(API_URL);
  }

  findById(id: string | null): Observable<Article> {
    console.log(API_URL + id);
    return this.http.get<Article>(API_URL + id);
  }
}
