import { Component, OnInit } from '@angular/core';
import {ArticleService} from "../_services/article.service";
import {User} from "../model/user";
import {Article} from "../model/article";
import { NavbarModule, WavesModule, ButtonsModule } from 'angular-bootstrap-md'

@Component({
  selector: 'app-article-list',
  templateUrl: './article-list.component.html',
  styleUrls: ['./article-list.component.css']
})
export class ArticleListComponent implements OnInit {

  articles: Article[] | undefined;

  constructor(private articleService: ArticleService) { }

  ngOnInit() {
    this.articleService.findAll().subscribe(data => {
      this.articles = data;
    });
  }

}
