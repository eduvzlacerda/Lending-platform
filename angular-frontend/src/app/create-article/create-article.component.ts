import { Component, OnInit } from '@angular/core';
import {ArticleService} from "../_services/article.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-article',
  templateUrl: './create-article.component.html',
  styleUrls: ['./create-article.component.css']
})
export class CreateArticleComponent implements OnInit {
  form: any = {
    title: null,
    description: null
  };

  constructor(private articleService: ArticleService, private router: Router) { }

  ngOnInit() {

  }

  onSubmit() {
    const { title, description } = this.form;
    // @ts-ignore
    let user = JSON.parse(localStorage.getItem('user'));
    this.articleService.create(title, description, user.id).subscribe({
        next: data => {
          this.router.navigate(['/articles/user']);
        }
      });
  }

}
