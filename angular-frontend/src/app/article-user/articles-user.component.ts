import { Component, OnInit } from '@angular/core';
import {ArticleService} from "../_services/article.service";
import {User} from "../model/user";
import {Article} from "../model/article";
import { NavbarModule, WavesModule, ButtonsModule } from 'angular-bootstrap-md'

@Component({
  selector: 'app-articles-user',
  templateUrl: './articles-user.component.html',
  styleUrls: ['./articles-user.component.css']
})
export class ArticlesUserComponent implements OnInit {

  articles!: Article[];
  searchString!: string;
  open: boolean = false;

  constructor(private articleService: ArticleService) { }

  ngOnInit() {
    // @ts-ignore
    let user = JSON.parse(localStorage.getItem('user'));
    this.articleService.findByUser(user.id).subscribe(data => {
      this.articles = data;
    });
  }

  delete(id: string) {
    this.articleService.delete(id).subscribe(data =>{
      this.articles = this.articles.filter(article => article.id != id);
    });
  }
}
