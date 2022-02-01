import { Component, OnInit } from '@angular/core';
import {ArticleService} from "../_services/article.service";
import {Article} from "../model/article";
import {ActivatedRoute, Router} from "@angular/router";
import {Observable} from "rxjs";

@Component({
  selector: 'app-edit-article',
  templateUrl: './edit-article.component.html',
  styleUrls: ['./edit-article.component.css']
})
export class EditArticleComponent implements OnInit {

  public article: Observable<Article> | undefined;
  private id!: string | null;
  title: string | undefined;
  description: string | undefined;
  form: any = {
    titleForm: null,
    descriptionForm: null
  };

  constructor(private articleService: ArticleService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.articleService.findById(this.id).subscribe(data =>{
      this.title = data.title;
      this.description = data.description;
    });
  }

  onSubmit() {
    let { titleForm, descriptionForm } = this.form;
    if (titleForm == "" || titleForm == null) {
      titleForm = this.title;
    }
    if (descriptionForm == "" || descriptionForm == null) {
      descriptionForm = this.description;
    }
    // @ts-ignore
    let user = JSON.parse(localStorage.getItem('user'));
    this.articleService.update(this.id, titleForm, descriptionForm, user.id).subscribe({
      next: data => {
        this.router.navigate(['/articles/user']);
      }
    });
  }

}
