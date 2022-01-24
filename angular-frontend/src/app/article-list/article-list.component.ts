import { Component, OnInit } from '@angular/core';
import {ArticleService} from "../_services/article.service";
import {Article} from "../model/article";
import {LendingprocessService} from "../_services/lendingprocess.service";
import {AuthService} from "../_services/auth.service";
import {User} from "../model/user";

@Component({
  selector: 'app-article-list',
  templateUrl: './article-list.component.html',
  styleUrls: ['./article-list.component.css']
})
export class ArticleListComponent implements OnInit {

  articles: Article[] | undefined;

  constructor(
      private articleService: ArticleService,
      private lendingprocessService: LendingprocessService,
      private authService: AuthService

  ) {}

  ngOnInit() {
    this.articleService.findAll().subscribe(data => {
      this.articles = data;
    });
  }

  borrow(article : Article) {

    if(confirm("Dou you want to borrow this article: " + article.title + "?")) {

      let user = this.authService.userValue;

      console.log(user)
      console.log(article)

      // this.lendingprocessService.findAll().subscribe(data => {
      //   console.log(data);
      // })

      this.lendingprocessService.addLendingProcess(article, this.authService.userValue).subscribe(
          data => {
            console.log(data)
          }
      )

    } else {

      console.log("no")

    }

  }

}
