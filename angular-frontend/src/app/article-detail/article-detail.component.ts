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

  article!: Article;
  private id: string | null | undefined;

  constructor(private articleService: ArticleService, private route: ActivatedRoute) { }

  ngOnInit() {

    this.article = new Article();

    this.id = this.route.snapshot.paramMap.get('id');

    console.log(this.id);

    this.articleService.findById(this.id)
      .subscribe(val => {
        console.log(val)
        this.article = val;
      });

    //console.log(this.article);

  }

  // public getArticle(): Observable<Article> {
  //   return this.article;
  // }

}
