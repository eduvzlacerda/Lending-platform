import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {Article} from "../model/article";

const API_URL = 'http://localhost:8080/lab/articles/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

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
    return this.http.get<Article>(API_URL + id);
  }

  findByUser(id: string): Observable<Article[]> {
    return this.http.get<Article[]>(API_URL + "user/" + id);
  }

  create(title: string, description: string, userId: string): Observable<any> {
    return this.http.post(API_URL, {
      title,
      description,
      userId
    }, httpOptions);
  }

  update(id: string | null, title: string, description: string, userId: string): Observable<any> {
    return this.http.post(API_URL + "update/", {
      id,
      title,
      description,
      userId
    }, httpOptions);
  }

  delete(id: string): Observable<any> {
    return this.http.get(API_URL + "delete/" + id);
  }
}
