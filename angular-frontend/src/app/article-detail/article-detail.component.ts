import { Component, OnInit } from '@angular/core';
import {Article} from "../model/article";
import {ArticleService} from "../_services/article.service";
import { ActivatedRoute } from '@angular/router';
import {concatMap, Observable} from "rxjs";

@Component({
  selector: 'app-article-detail',
  templateUrl: './article-detail.component.html',
  styleUrls: ['./article-detail.component.css']
})
export class ArticleDetailComponent implements OnInit {

  private article: Observable<Article> | undefined;
  private id: string | null | undefined;

  constructor(private articleService: ArticleService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.article = this.articleService.findById(this.id);
  }

}
